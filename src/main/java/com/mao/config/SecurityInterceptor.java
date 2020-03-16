package com.mao.config;

import com.mao.service.security.SecurityService;
import com.mao.service.sys.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 安全拦截器
 * @author mao by 18:15 2020/3/12
 */
@Component
public class SecurityInterceptor implements HandlerInterceptor {

    private SecurityService securityService;
    private LogService logService;

    @Autowired
    public void setSecurityService(SecurityService securityService){
        this.securityService = securityService;
    }
    @Autowired
    public void setLogService(LogService logService){
        this.logService = logService;
    }

    /**
     * 拦截方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod) handler;
        try {
            securityService.authorization(request);
            logService.saveLog(request,method,true,null);
        } catch (SecurityException e) {
            logService.saveLog(request,method,false,e.getMessage());
            throw e;
        }
        return true;
    }

}