package com.woniuxy.commons.interceptor.configuration;

import com.woniuxy.commons.interceptor.AuthInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @projectName: springcloud86
 * @package: com.woniuxy.commons.interceptor.configuration
 * @className: InterceptorConfiguration
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/5/19 10:26
 * @version: 1.0
 */
@Component
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Resource
    AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将拦截器添加到拦截器列表中拦截器才会生效，同时指定拦截范围，因为拦截器需要拦截多个微服务是一个通用的拦截器
        registry.addInterceptor(authInterceptor).addPathPatterns("/**");
    }
}
