package com.woniuxy.supply.controller;

import com.woniuxy.commons.entity.DTO.SupplyDTO;
import com.woniuxy.commons.entity.PageInfomation;
import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.supply.service.SupplyService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/all")
    public ResponseResult findAllSupply(@RequestBody PageInfomation pageInfomation) {
        return supplyService.findAllSupply(pageInfomation);
    }

    /**
     * @param
     * @return ResponseResult
     * @description 根据核心企业id查询供应链
     * @author qfx
     * @date 2022/6/7 19:43
     */
    @GetMapping("/findById/{coreId}")
    public ResponseResult findById(@PathVariable("coreId") int coreId) {
        return supplyService.findById(coreId);
    }

    @PostMapping("/findByEid")

    /**
     * @description 根据企业id分页查询供应链
     * @author qfx
     * @date 2022/6/9 14:50
     * @param supplyDTO
     * @return ResponseResult
     */
    public ResponseResult findByEid(@RequestBody SupplyDTO supplyDTO) {
        return supplyService.findByEid(supplyDTO);
    }

    /**
     * @param supplyDTO
     * @return ResponseResult
     * @description 条件查询供应链
     * @author qfx
     * @date 2022/6/9 12:03
     */
    @PostMapping("/findBy")
    public ResponseResult findByCondition(@RequestBody SupplyDTO supplyDTO) {
        return supplyService.findByCondition(supplyDTO);
    }

    /**
     * @param supplyDTO
     * @return ResponseResult
     * @description 根据id条件查询供应链
     * @author qfx
     * @date 2022/6/9 15:47
     */
    @PostMapping("/findByConditionAndEid")
    public ResponseResult findByConditionAndEid(@RequestBody SupplyDTO supplyDTO) {
        return supplyService.findByConditionAndEid(supplyDTO);
    }
}
