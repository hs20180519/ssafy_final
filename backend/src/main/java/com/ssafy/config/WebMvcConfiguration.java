package com.ssafy.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@MapperScan(basePackages = { "com.ssafy.**.mapper" })
public class WebMvcConfiguration implements WebMvcConfigurer {


//    @Autowired
//    private AdminInterceptor adminInterceptor;


//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/*");
//    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

}
