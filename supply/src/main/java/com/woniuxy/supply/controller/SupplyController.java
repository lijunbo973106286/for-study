package com.woniuxy.supply.controller;

import com.woniuxy.commons.entity.PageInfomation;
import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.supply.service.SupplyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.supply.controller
 * @Author qfx
 * @CreateTime 2022-06-07  11:02
 * @Description 供应链管理
 * @Version 1.0
 */
@RestController
@RequestMapping("/supply")
public class SupplyController {
    @Resource
    SupplyService supplyService;

    /**
     * @param pageInfomation
     * @return ResponseResult
     * @description 查询所有供应链
     * @author qfx
     * @date 2022/6/7 19:43
     */
    @GetMapping("/all")
    public ResponseResult findAllSupply(@RequestBody PageInfomation pageInfomation) {
        return supplyService.findAllSupply(pageInfomation);
    }

}
