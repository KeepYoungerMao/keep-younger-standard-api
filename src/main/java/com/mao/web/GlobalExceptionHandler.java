package com.mao.web;

import com.mao.entity.ResponseData;
import com.mao.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 * @author mao by 18:40 2020/3/12
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private BaseService baseService;

    @Autowired
    public void setBaseService(BaseService baseService){
        this.baseService = baseService;
    }

    /**
     * 安全异常
     */
    @ExceptionHandler(SecurityException.class)
    public ResponseData handleSecurityException(SecurityException e){
        return baseService.permission(e.getMessage());
    }

    /**
     * 404,500错误捕捉
     */
    @ExceptionHandler(Exception.class)
    public ResponseData exceptionHandler(HttpServletRequest request, Exception e){
        if (e instanceof NoHandlerFoundException)
            return baseService.bad("not found source: "+request.getRequestURI());
        else{
            e.printStackTrace();
            return baseService.error("server is busy. please try again later");
        }
    }

    /**
     * 请求类型不支持异常捕捉
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseData notSupportExceptionHandler(HttpRequestMethodNotSupportedException e){
        return baseService.bad(e.getMessage());
    }

}