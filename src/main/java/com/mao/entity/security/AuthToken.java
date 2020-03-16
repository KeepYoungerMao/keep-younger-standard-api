package com.mao.entity.security;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * token数据
 * @author mao by 10:56 2020/3/13
 */
@Getter
@Setter
public class AuthToken implements Serializable {

    private static final long serialVersionUID = 11223344L;

    private String token;
    private String refresh_token;
    private long expire;
    private long timestamp;

}