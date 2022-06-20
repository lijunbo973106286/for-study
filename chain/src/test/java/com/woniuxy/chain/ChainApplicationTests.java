package com.woniuxy.chain;

import com.woniuxy.chain.service.ScfpChainService;
import com.woniuxy.commons.entity.PageInfomation;
import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpChain;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ChainApplicationTests {

    @Resource
    ScfpChainService service;
    @Test
    void contextLoads() {
        ScfpChain scfpChain = new ScfpChain();
        scfpChain.setId(1);
        ResponseResult<ScfpChain> result = service.findById(scfpChain);
        System.out.println(result);
    }

}
