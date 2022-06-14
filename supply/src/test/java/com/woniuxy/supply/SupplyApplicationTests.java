package com.woniuxy.supply;

import com.woniuxy.commons.entity.ScfpChain;
import com.woniuxy.commons.service.ScfpChainService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SupplyApplicationTests {

    @Resource
    private ScfpChainService scfpChainService;

    @Test
    void contextLoads() {
        ScfpChain scfpChain = new ScfpChain();
        scfpChain.setId(1);
        ScfpChain data = scfpChainService.findById(scfpChain).getData();
        System.out.println(data);
    }

}
