package com.woniuxy.fiance.controller;

import com.woniuxy.commons.entity.DTO.EnterpriseAccountDTO;
import com.woniuxy.commons.entity.ScfpEnterpriseAccount;
import com.woniuxy.commons.util.ResponseResult;
import com.woniuxy.fiance.service.fianceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/fiance")
public class fiancecontroller {
    @Resource
    private fianceService fianceService;
    //查询用户银行信息
    @GetMapping("/bankaccount/{currentPate}/{pageSize}/{id}")
    public ResponseResult findbankaccount(@PathVariable("currentPate")int currentPate,@PathVariable("pageSize")int pageSize,@PathVariable("id") int id){
        return   fianceService.findbankaccount(currentPate,pageSize,id);
    }
    //修改银行信息
    @PostMapping("/modifyaccount")
    public ResponseResult modifyaccount(@RequestBody ScfpEnterpriseAccount scfpEnterpriseAccount){
        return   fianceService.modifyaccount(scfpEnterpriseAccount);
    }
    //删除银行信息
    @PostMapping("/delaccount/{id}")
    public ResponseResult delaccount(@PathVariable("id") int seaid){
        return fianceService.delaccount(seaid);
    }
    //增加银行信息
    @PostMapping("/addaccount")
    public ResponseResult addaccount(@RequestBody ScfpEnterpriseAccount scfpEnterpriseAccount){
        return fianceService.add(scfpEnterpriseAccount);
    }



}
