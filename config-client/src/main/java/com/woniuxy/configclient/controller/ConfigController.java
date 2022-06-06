package com.woniuxy.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject alibaba
 * @BelongsPackage com.woniuxy.configclient.controller
 * @Author qfx
 * @CreateTime 2022-06-01  10:34
 * @Description TODO
 * @Version 1.0
 */
@RestController
@RefreshScope
@RequestMapping("/conf")
public class ConfigController {
    @Value("${server.port}")
    private int port;

    @RequestMapping("/port")
    public int getPort() {
        return port;
    }
}
