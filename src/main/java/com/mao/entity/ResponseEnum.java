package com.mao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author mao by 17:26 2019/8/20
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {

    SUCCESS(200),       //成功类型
    NOT_FOUND(404),     //404类型
    SECURITY(405),      //验证错误类型
    BAD_REQUEST(406),   //请求错误类型
    ERROR(500);         //系统错误类型

    private int code;

}