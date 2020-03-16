package com.mao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC 配置类
 * @author mao by 18:14 2020/3/12
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private SecurityInterceptor securityInterceptor;

    @Autowired
    public void setSecurityInterceptor(SecurityInterceptor securityInterceptor){
        this.securityInterceptor = securityInterceptor;
    }

    /**
     * 注册拦截器
     * @param registry 注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor).addPathPatterns("/api/**");
    }

    /**
     * 跨域配置
     * @param registry 注册器
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("GET","POST","PUT")
                .allowedOrigins("*");
    }

}