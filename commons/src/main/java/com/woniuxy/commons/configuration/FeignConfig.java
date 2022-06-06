package com.woniuxy.commons.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @projectName: springcloud86
 * @package: com.woniuxy.commons.configuration
 * @className: FeignConfig
 * @author: SuYHo
 * @description: 将请求头中的token放到openfeign的请求头中
 * @date: 2022/5/16 20:51
 * @version: 1.0
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
        template.header("refreshToken", request.getHeader("refreshToken"));
    }
}