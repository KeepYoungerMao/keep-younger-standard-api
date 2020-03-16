package com.mao.entity.security;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 用户参数
 * @author mao by 11:04 2020/3/13
 */
@Getter
@Setter
public class UserDetails implements Serializable {

    private static final long serialVersionUID = 22334455L;

    private String app_name;
    private Long app_key;
    private String app_secret;
    private Long app_role;
    private String app_role_name;
    private boolean locked;
    private boolean expired;
    private boolean enabled;
    private List<String> permissions;

}