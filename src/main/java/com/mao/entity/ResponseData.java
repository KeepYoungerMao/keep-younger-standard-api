package com.mao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author mao by 18:24 2020/3/12
 */
@Getter
@Setter
@AllArgsConstructor
public class ResponseData<T> {

    private int code;       //状态码
    private String msg;     //状态信息
    private T data;         //数据

}