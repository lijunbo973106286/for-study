package com.woniuxy.supply;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.woniuxy.commons.service")
public class SupplyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupplyApplication.class, args);
    }

}
