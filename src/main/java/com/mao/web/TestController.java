package com.mao.web;

import com.mao.service.sys.log.Log;
import com.mao.service.sys.log.LogEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mao by 18:17 2020/3/12
 */
@RestController
@RequestMapping("api")
public class TestController {

    @Log(LogEnum.TEST)
    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

}