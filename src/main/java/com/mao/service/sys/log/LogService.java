package com.mao.service.sys.log;

import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志服务
 * @author mao by 11:30 2020/3/16
 */
public interface LogService {

    /**
     * 日志的保存
     * @param request HttpServletRequest
     * @param method HandlerMethod 拦截器参数handler
     * @param success 请求是否成功
     * @param error 错误信息
     */
    void saveLog(HttpServletRequest request, HandlerMethod method, boolean success, String error);

}