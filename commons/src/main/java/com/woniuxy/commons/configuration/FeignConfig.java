package com.woniuxy.commons.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @BelongsProject: myspringcloud
 * @BelongsPackage: com.woniuxy.commons.configuration
 * @Author: qfx
 * @CreateTime: 2022-05-19  14:30
 * @Description: 在openfeign请求头上添加token
 * @Version: 1.0
 */
@Configuration
public class FeignConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        //获取到request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //将token添加到头上
        template.header("Authorization", request.getHeader("Authorization"));
    }
}