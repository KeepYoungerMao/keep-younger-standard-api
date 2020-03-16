package com.mao.web;

import com.mao.config.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统类请求
 * @author mao by 16:53 2020/3/13
 */
@RestController
public class SystemController {

    private Server server;

    @Autowired
    public void setServer(Server server){
        this.server = server;
    }

    @GetMapping("")
    public Server server(){
        return server;
    }

}