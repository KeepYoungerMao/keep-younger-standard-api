package com.mao.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 服务器参数
 * 详细参数信息见服务器配置文件
 * @author mao by 17:13 2020/3/13
 */
@Getter
@Setter
@Component
@PropertySource("classpath:server.conf")
@ConfigurationProperties(prefix = "server")
public class Server {

    private String name;
    private String company;
    private String version;
    private String description;
    private String status;
    private Long start_time;
    private String java_version;
    private String redis_version;

    public Server(){
        this.start_time = System.currentTimeMillis();
    }

}