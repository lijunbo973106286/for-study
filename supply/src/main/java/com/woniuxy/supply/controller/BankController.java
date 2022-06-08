package com.woniuxy.supply.controller;

import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.supply.service.BankService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.supply.controller
 * @Author qfx
 * @CreateTime 2022-06-08  14:14
 * @Description 资金方管理
 * @Version 1.0
 */
@RestController
@RequestMapping("/bank")
public class BankController {
    @Resource
    BankService bankService;
    /**
     * @param
     * @return ResponseResult
     * @description 查询所有融资机构
     * @author qfx
     * @date 2022/6/7 19:42
     */
    @PostMapping("/allBank")
    public ResponseResult findAllBank() {
        return bankService.findAllBank();
    }
}
