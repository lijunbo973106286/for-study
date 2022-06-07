package com.woniuxy.chain;

import com.woniuxy.chain.service.ScfpChainService;
import com.woniuxy.commons.entity.PageInfomation;
import com.woniuxy.commons.entity.ResponseResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ChainApplicationTests {

    @Resource
    ScfpChainService service;
    @Test
    void contextLoads() {

    }

}
