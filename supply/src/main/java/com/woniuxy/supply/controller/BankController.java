package com.woniuxy.supply.controller;

import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpAmount;
import com.woniuxy.supply.service.BankService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.supply.controller
 * @Author qfx
 * @CreateTime 2022-06-08  14:14
 * @Description 额度管理
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

    @PostMapping("/allAmount")
    public ResponseResult allAmount(@RequestBody ScfpAmount scfpAmount) {
        return bankService.allAmount(scfpAmount);
    }

    @PostMapping("/findTotal")
    public ResponseResult findTotal(@RequestBody ScfpAmount scfpAmount) {
        return bankService.findTotal(scfpAmount);
    }

    @PostMapping("/findBy")
    public ResponseResult findBy(@RequestBody ScfpAmount scfpAmount) {
        return bankService.findBy(scfpAmount);
    }

    @PutMapping("/credit/{eid}")
    public ResponseResult credit(@PathVariable("eid") int eid) {
        return bankService.credit(eid);
    }
}
