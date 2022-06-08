package com.woniuxy.supply;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.woniuxy.commons.service")
@MapperScan("com.woniuxy.supply.dao")
public class SupplyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SupplyApplication.class, args);
    }

}
