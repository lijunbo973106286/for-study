package com.woniuxy.chain.controller;

import com.woniuxy.chain.service.ScfpChainService;
import com.woniuxy.commons.entity.*;
import com.woniuxy.commons.service.ScfpFileService;
import com.woniuxy.commons.util.ConvertTime;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.io.IOException;
import java.util.List;

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
    @Resource
    ScfpFileService scfpFileService;
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
    @PostMapping("/search")
    public ResponseResult<ScfpChain> search(@RequestBody ScfpChain scfpChain){
        return scfpChainService.search(scfpChain);
    }

    /** 分页查询所有链单信息 */
    @PostMapping("/findAll")
    public ResponseResult<Object> findAll(@RequestBody ScfpChain scfpChain){
        return scfpChainService.findAll(scfpChain);
    }

    /** 查找所有链单数量 */
    @GetMapping("/findAllCount/{eid}")
    public ResponseResult<Object> findAllCount(@PathVariable("eid") int eid){
        return scfpChainService.findAllCount(eid);
    }
    /** 查找各分类链单数量 */
    @PostMapping("/findCount")
    public ResponseResult<Object> findCount(@RequestBody ScfpChain scfpChain){
        return scfpChainService.findCount(scfpChain);
    }

    /** 单个兑付处理 */
    @PutMapping("/updateLoan/{chain_id}")
    public ResponseResult<Object> updateLoan(@PathVariable("chain_id") int chain_id) {
        return scfpChainService.updateLoan(chain_id);
    }

    /** 批处理兑付 */
    @PutMapping("/updateBatLoan")
    public ResponseResult<Object> updateLoan(@RequestBody List<Integer> ids) {
        return scfpChainService.updateBatLoan(ids);
    }

    /** 检查交易密码 */
    @PostMapping("/checkPayPass")
    public ResponseResult<Object> checkPayPass(@RequestBody ScfpEnterprise scfpEnterprise) {
        return scfpChainService.checkPayPass(scfpEnterprise);
    }
    /** 查找银行相关信息*/
    @GetMapping("/getEnterprise/{bankName}")
    public ResponseResult<ScfpEnterprise> getEnterprise(@PathVariable("bankName") String bankName){
        return scfpChainService.getEnterprise(bankName);
    }

    /** 一次查询未兑付及未兑付的链单信息 */
    @PostMapping("/findAllLoan")
    public ResponseResult<ScfpEnterprise> findAllLoan(@RequestBody ScfpChain scfpChain){
        return scfpChainService.findAllLoan(scfpChain);
    }

    /** 查找所有未兑付和已兑付链单数量 */
    @PostMapping("/findLoanCount")
    public ResponseResult<Object> findLoanCount(@RequestBody ScfpChain scfpChain){
        return scfpChainService.findLoanCount(scfpChain);
    }

}
