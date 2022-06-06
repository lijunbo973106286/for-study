package com.woniuxy.commons.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @BelongsProject: myspringcloud
 * @BelongsPackage: com.woniuxy.commons.configuration
 * @Author: qfx
 * @CreateTime: 2022-05-19  10:26
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Resource
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/**");
    }
}
