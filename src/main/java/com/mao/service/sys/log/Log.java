package com.mao.service.sys.log;

import java.lang.annotation.*;

/**
 * @author mao by 11:52 2020/3/16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    LogEnum value();

}