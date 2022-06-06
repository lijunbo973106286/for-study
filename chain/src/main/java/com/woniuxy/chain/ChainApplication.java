package com.woniuxy.chain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.woniuxy.commons.service")

public class ChainApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChainApplication.class, args);
    }

}
