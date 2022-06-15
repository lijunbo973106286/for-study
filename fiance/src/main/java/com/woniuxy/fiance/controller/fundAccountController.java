package com.woniuxy.fiance.controller;

import com.woniuxy.commons.entity.ScfpFundAccount;
import com.woniuxy.commons.util.ResponseResult;
import com.woniuxy.fiance.service.FAccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/faccount")
public class fundAccountController {
    @Resource
    FAccountService fAccountService;
    //添加资金账户
    @PostMapping("/add")
    public ResponseResult add(@RequestBody ScfpFundAccount scfpFundAccount){
        return fAccountService.add(scfpFundAccount);
    }
    //修改资金账户
    @PostMapping("/update/{pay_pass}/{id}")
    public ResponseResult update(@PathVariable("pay_pass") int pay_pass,@PathVariable("id") int id){
        return fAccountService.update(pay_pass,id);
    }
    //删除资金账户
    @PostMapping("/del/{id}")
    public ResponseResult del(@PathVariable("id") int id){
        return fAccountService.del(id);
    }
    //查询自己的资金账户
    @PostMapping("/findID/{id}")
    public ResponseResult findID(@PathVariable("id") int id){
        return fAccountService.findID(id);
    }

    //查询所有资金账户
    @PostMapping("/findAll/{currentPate}/{pageSize}")
    public ResponseResult findAll(@PathVariable("currentPate") int currentPate,@PathVariable("pageSize") int pageSize){
        return fAccountService.findAll(currentPate,pageSize);
    }
    //激活资金账户
    @PostMapping("/activation/{id}")
    public ResponseResult activation(@PathVariable("id") int id){
        return fAccountService.activation(id);
    }
    //冻结资金账户
    @PostMapping("/freeze/{id}")
    public ResponseResult freeze(@PathVariable("id") int id){
        return fAccountService.freeze(id);
    }

}
