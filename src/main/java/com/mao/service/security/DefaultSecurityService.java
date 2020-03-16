package com.mao.service.security;

import com.mao.entity.security.AuthToken;
import com.mao.entity.security.UserDetails;
import com.mao.mapper.SystemMapper;
import com.mao.service.BaseService;
import com.mao.util.SU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 安全事务
 * @author mao by 10:37 2020/3/13
 */
@Service
public class DefaultSecurityService extends BaseService implements SecurityService {

    private static final String AUTHORIZATION = "Authorization";
    private static final String TOKEN_PRE = "token_";
    private static final String REFRESH_TOKEN_PRE = "refresh_token_";
    private static final long EXPIRE = 7200;

    private RedisTemplate<Object, Object> redisTemplate;
    private SystemMapper systemMapper;

    @Autowired
    public void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate){
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        this.redisTemplate = redisTemplate;
    }
    @Autowired
    public void setSystemMapper(SystemMapper systemMapper){
        this.systemMapper = systemMapper;
    }

    /**
     * 验证
     * @param request request
     */
    public void authorization(HttpServletRequest request){
        String authorization = request.getHeader(AUTHORIZATION);
        if (SU.isEmpty(authorization))
            throw new SecurityException("authorization is null");
        UserDetails userDetails = getUserDetails(authorization);
        if (null == userDetails)
            throw new SecurityException("expired token");
        String url = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
        checkUser(userDetails,url);
    }

    /**
     * 用户的验证
     * @param userDetails 用户信息
     * @param url 当前访问地址匹配
     * @throws SecurityException e
     */
    private void checkUser(UserDetails userDetails, String url) {
        if (userDetails.isLocked())
            throw new SecurityException("account locked");
        if (userDetails.isExpired())
            throw new SecurityException("account expired");
        if (!userDetails.isEnabled())
            throw new SecurityException("account cannot use");
        if (userDetails.getPermissions().size() <= 0)
            throw new SecurityException("no permission");
        boolean noMapping = true;
        for (String s : userDetails.getPermissions()) {
            if (url.equals(s)){
                noMapping = false;
                break;
            }
        }
        if (noMapping)
            throw new SecurityException("no permission");
    }

    /**
     * 根据refresh_token刷新token
     * @param refreshToken refresh_token
     * @return AuthToken
     * @throws SecurityException e
     */
    public AuthToken getToken(String refreshToken) throws SecurityException {
        //从redis获取AuthToken和UserDetails
        AuthToken token = getAuthToken(refreshToken);
        if (null == token)
            throw new SecurityException("invalid refresh token");
        UserDetails userDetails = getUserDetails(token.getToken());
        if (null == userDetails)
            throw new SecurityException("token expired");
        //删除旧数据
        deleteToken(token);
        //生成新数据
        AuthToken authToken = makeAuthToken();
        //储存新数据
        UserDetails details = getUserDetails(userDetails.getApp_key());
        saveToken(authToken,details);
        return authToken;
    }

    /**
     * 根据app_id和app_secret获取token
     * @param appKey app_key
     * @param appSecret app_secret
     * @return AuthToken
     */
    public AuthToken getToken(Long appKey, String appSecret) throws SecurityException{
        //数据验证
        if (null == appKey || appKey <= 0)
            throw new SecurityException("loss param app_key");
        if (SU.isEmpty(appSecret))
            throw new SecurityException("loss param app_secret");
        UserDetails userDetails = getUserDetails(appKey);
        if (null == userDetails)
            throw new SecurityException("invalid app_id");
        if (!appSecret.equals(userDetails.getApp_secret()))
            throw new SecurityException("invalid app_secret");
        //数据无误，组合authToken
        AuthToken authToken = makeAuthToken();
        //保存token数据至redis
        saveToken(authToken,userDetails);
        return authToken;
    }

    /**
     * 从redis中获取token数据
     * @param refreshToken refreshToken
     * @return AuthToken
     */
    private AuthToken getAuthToken(String refreshToken){
        Object o = redisTemplate.opsForValue().get(REFRESH_TOKEN_PRE + refreshToken);
        System.out.println(o);
        return o instanceof AuthToken ? (AuthToken)o : null;
    }

    /**
     * 从redis中获取用户数据
     * @param token token
     * @return UserDetails
     */
    private UserDetails getUserDetails(String token){
        Object o = redisTemplate.opsForValue().get(TOKEN_PRE + token);
        System.out.println(o);
        return o instanceof UserDetails ? (UserDetails)o : null;
    }

    /**
     * 保存token数据至redis
     * @param authToken  AuthToken
     * @param userDetails UserDetails
     */
    private void saveToken(AuthToken authToken, UserDetails userDetails){
        String token = TOKEN_PRE + authToken.getToken();
        String refresh = REFRESH_TOKEN_PRE + authToken.getRefresh_token();
        redisTemplate.opsForValue().set(token,userDetails,EXPIRE,TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(refresh,authToken,EXPIRE,TimeUnit.SECONDS);
    }

    /**
     * 从redis中删除token数据
     * @param authToken AuthToken
     */
    private void deleteToken(AuthToken authToken){
        redisTemplate.delete(TOKEN_PRE + authToken.getToken());
        redisTemplate.delete(REFRESH_TOKEN_PRE + authToken.getRefresh_token());
    }

    /**
     * 组装authToken
     * @return AuthToken
     */
    private AuthToken makeAuthToken(){
        AuthToken authToken = new AuthToken();
        authToken.setToken(SU.uuid());
        authToken.setRefresh_token(SU.uuid());
        authToken.setExpire(EXPIRE);
        authToken.setTimestamp(System.currentTimeMillis());
        return authToken;
    }

    /**
     * 查询用户详情
     * @param appKey app_id
     * @return UserDetails
     */
    private UserDetails getUserDetails(Long appKey){
        UserDetails userDetail = systemMapper.getUserDetail(appKey);
        if (null != userDetail) {
            List<String> permissions = systemMapper.getPermissions(userDetail.getApp_role());
            userDetail.setPermissions(permissions);
        }
        return userDetail;
    }

}