package com.woniuxy.loan.controller;

import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpLoan;
import com.woniuxy.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @program: supply-chain-finance
 * @description: 放款相关操作控制器
 * @author: Linqiao Dai
 * @create: 2022-06-07 12:01
 **/
@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    //申请放款
    @PostMapping("/apply")
    public ResponseResult<Object> applyLoan(@RequestBody ScfpLoan scfpLoan){
        return loanService.applyLoan(scfpLoan);
    }

}
