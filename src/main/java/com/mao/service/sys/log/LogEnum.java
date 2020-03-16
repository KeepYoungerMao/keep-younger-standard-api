package com.mao.service.sys.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author mao by 11:54 2020/3/16
 */
@Getter
@AllArgsConstructor
public enum LogEnum {

    TEST("测试"),
    USER("用户数据");

    private String data_type;

}