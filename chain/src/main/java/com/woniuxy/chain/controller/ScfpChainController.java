package com.woniuxy.chain.controller;

import com.woniuxy.chain.service.ScfpChainService;
import com.woniuxy.commons.entity.PageInfomation;
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

    /** 通过传入的chain对象插入scfp_chain表中，没有的信息默认为null或者0 */
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
    /** 通过id进行软删除*/
    @DeleteMapping("/delete/{id}")
    public  ResponseResult<Object> delete(@PathVariable("id") int id){
        return scfpChainService.delete(id);
    }

    /** 通过scfpchain对象动态查询ScfpChain对象*/
    @GetMapping("/search")
    public ResponseResult<ScfpChain> search(@RequestBody ScfpChain scfpChain){
        return scfpChainService.search(scfpChain);
    }

    /** 分页查询所有链单信息 */
    @PostMapping("/findAll")
    public ResponseResult<Object> findAll(@RequestBody PageInfomation pageInfomation){
        return scfpChainService.findAll(pageInfomation);
    }

}
