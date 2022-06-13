package com.woniuxy.fiance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients("com.woniuxy.commons.service")
@EnableScheduling
public class FianceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FianceApplication.class, args);
    }

}
