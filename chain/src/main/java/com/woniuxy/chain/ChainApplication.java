package com.woniuxy.chain;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.woniuxy")
@EnableFeignClients("com.woniuxy.commons.service")
@MapperScan("com.woniuxy.chain.dao")
public class ChainApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChainApplication.class, args);
    }

}
