/*
 * @Title: project
 * @Description: TODO
 * @author MI
 * @date 2022/8/4 12:07
 * @version
 */
package com.imooc.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author MI
 * @ClassName: WebConfig
 * @Description: TODO
 * @date 2022/8/4 12:07
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        //浏览器直接跳转到登录页
        registry.addViewController("/").setViewName("/login/login");
    }
}
