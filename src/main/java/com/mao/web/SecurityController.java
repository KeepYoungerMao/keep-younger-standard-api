package com.mao.web;

import com.mao.entity.security.AuthToken;
import com.mao.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * token请求
 * @author mao by 15:43 2020/3/13
 */
@RestController
@RequestMapping("auth")
public class SecurityController {

    private SecurityService securityService;

    @Autowired
    public void setSecurityService(SecurityService securityService){
        this.securityService = securityService;
    }

    /**
     * token获取
     * @param app_key app_key
     * @param app_secret app_secret
     * @return AuthToken
     */
    @GetMapping("token")
    public AuthToken getToken(Long app_key, String app_secret){
        return securityService.getToken(app_key,app_secret);
    }

    /**
     * token刷新
     * @param refresh_token refresh_token
     * @return AuthToken
     */
    @GetMapping("refresh")
    public AuthToken refreshToken(String refresh_token){
        return securityService.getToken(refresh_token);
    }

}