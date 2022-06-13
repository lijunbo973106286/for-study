package com.woniuxy.commons.service;

import com.alibaba.fastjson.JSON;
import com.woniuxy.commons.entity.*;
import com.woniuxy.commons.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-07 10:28
 * \* @Description：
 */
@FeignClient(name = "chain")
public interface ScfpChainService {

    /** 通过传入的chain对象插入scfp_chain表中，没有的信息默认为null或者0 */
    @PostMapping("/chain/insert")
    public ResponseResult<Object> insert(@RequestBody ScfpChain scfpChain);

    /**
     * 通过传入chain对象动态修改scfp_chain表中的信息
     */
    @PutMapping("/chain/update")
    public ResponseResult<Object> update(@RequestBody ScfpChain scfpChain);
    /** 通过id进行软删除*/
    @DeleteMapping("/chain/delete/{id}")
    public  ResponseResult<Object> delete(@PathVariable("id") int id);

    /** 通过scfpchain对象动态查询ScfpChain对象*/
    @PostMapping("/chain/search")
    public ResponseResult<ScfpChain> search(@RequestBody ScfpChain scfpChain);

    /** 分页查询所有链单信息 */
    @PostMapping("/chain/findAll")
    public ResponseResult<Object> findAll(@RequestBody ScfpChain scfpChain);

    /** 查找所有链单数量 */
    @GetMapping("/chain/findAllCount")
    public ResponseResult<Object> findAllCount();
    /** 查找各分类链单数量 */
    @GetMapping("/chain/findCount/{status}")
    public ResponseResult<Object> findCount(@PathVariable("status") String status);

    /** 单个兑付处理 */
    @PutMapping("/chain/updateLoan/{chain_id}")
    public ResponseResult<Object> updateLoan(@PathVariable("chain_id") int chain_id);

    /** 批处理兑付 */
    @PutMapping("/chain/updateBatLoan")
    public ResponseResult<Object> updateLoan(@RequestBody List<Integer> ids);

    /** 检查交易密码 */
    @PostMapping("/chain/checkPayPass")
    public ResponseResult<Object> checkPayPass(@RequestBody ScfpEnterprise scfpEnterprise);

    /** 查找银行相关信息*/
    @GetMapping("/chain/getEnterprise/{bankName}")
    public ResponseResult<ScfpEnterprise> getEnterprise(@PathVariable("bankName") String bankName);

    /** 一次查询未兑付及未兑付的链单信息 */
    @PostMapping("/chain/findAllLoan")
    public ResponseResult<ScfpEnterprise> findAllLoan(@RequestBody ScfpChain scfpChain);

    /** 查找所有未兑付和已兑付链单数量 */
    @PostMapping("/chain/findLoanCount")
    public ResponseResult<Object> findLoanCount(@RequestBody ScfpChain scfpChain);

    /** 根据链单id查询链单详细信息 */
    @PostMapping("/chain/findById")
    public ResponseResult<ScfpChain> findById(@RequestBody ScfpChain scfpChain);

}
