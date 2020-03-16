package com.mao.service.sys.log;

import com.mao.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志服务
 * @author mao by 11:31 2020/3/16
 */
@Service
public class DefaultLogService extends BaseService implements LogService {

    /**
     * 日志的保存
     * @param request HttpServletRequest
     * @param method HandlerMethod 拦截器参数handler
     * @param success 请求是否成功
     * @param error 错误信息
     */
    @Override
    public void saveLog(HttpServletRequest request, HandlerMethod method, boolean success, String error) {
        //todo save log data
    }

}