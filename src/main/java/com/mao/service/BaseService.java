package com.mao.service;

import com.mao.entity.ResponseData;
import com.mao.entity.ResponseEnum;
import org.springframework.stereotype.Service;

/**
 * 基本service
 * @author mao by 18:26 2020/3/12
 */
@Service
public class BaseService {

    public static final String OK = "ok";

    public <T> ResponseData ok(T data){
        return o(ResponseEnum.SUCCESS,OK,data);
    }

    public ResponseData bad(String msg){
        return o(ResponseEnum.NOT_FOUND,msg,null);
    }

    public ResponseData warn(String msg){
        return o(ResponseEnum.BAD_REQUEST,msg,null);
    }

    public ResponseData error(String msg){
        return o(ResponseEnum.ERROR,msg,null);
    }

    public ResponseData permission(String msg){
        return o(ResponseEnum.SECURITY,msg,null);
    }

    private <T> ResponseData<T> o(ResponseEnum type, String msg, T data){
        return new ResponseData<>(type.getCode(),msg,data);
    }

}