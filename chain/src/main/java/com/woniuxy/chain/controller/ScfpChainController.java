package com.woniuxy.chain.controller;

import com.woniuxy.chain.service.ScfpChainService;
import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpChain;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-07 10:28
 * \* @Description：
 */
@RestController
@RequestMapping("/chain")
public class ScfpChainController {

    @Resource
    ScfpChainService scfpChainService;

    @PostMapping("/insert")
    public ResponseResult<Object> insert(@RequestBody ScfpChain scfpChain) {
        return scfpChainService.insert(scfpChain);
    }

    /**
     * 通过传入chain对象动态修改scfp_chain表中的信息
     */
    @PutMapping("/update")
    public ResponseResult<Object> update(@RequestBody ScfpChain scfpChain) {
        return scfpChainService.update(scfpChain);
    }
}
