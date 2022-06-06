package com.woniuxy.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@ComponentScan(basePackages = "com.woniuxy",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = "com.woniuxy.commons.interceptor.*")
)
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
