package com.mao.service.security;

import com.mao.entity.security.AuthToken;

import javax.servlet.http.HttpServletRequest;

/**
 * 安全事务
 * @author mao by 11:36 2020/3/13
 */
public interface SecurityService {

    /**
     * token验证
     * @param request request
     * @exception SecurityException 验证失败抛出异常
     */
    void authorization(HttpServletRequest request);

    /**
     * 根据refresh_token刷新token
     * @param refreshToken refresh_token
     * @return AuthToken
     * @throws SecurityException e
     */
    AuthToken getToken(String refreshToken) throws SecurityException;

    /**
     * 根据app_id和app_secret获取token
     * @param appKey app_key
     * @param appSecret app_secret
     * @return AuthToken
     */
    AuthToken getToken(Long appKey, String appSecret) throws SecurityException;

}