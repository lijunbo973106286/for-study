package com.woniuxy.loan.controller;

import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpLoan;
import com.woniuxy.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @program: supply-chain-finance
 * @description: 放款相关操作控制器
 * @author: Linqiao Dai
 * @create: 2022-06-07 12:01
 **/
@RestController
@RequestMapping("/loan")
public class LoanController {

    @Resource
    private LoanService loanService;

    //兑付
    @PostMapping("/apply")
    public ResponseResult<Object> applyLoan(@RequestBody ScfpLoan scfpLoan){
        return loanService.applyLoan(scfpLoan);
    }

    //还款
    @GetMapping("/repayment/{id}")
    public ResponseResult<Object> repayment(@PathVariable int id){
        return loanService.repayment(id);
    }

    //支付利息
    @GetMapping("/interest_payment/{id}")
    public ResponseResult<Object> interest(@PathVariable int id){
        return loanService.interest(id);
    }

    //支付手续费
    @GetMapping("/service_payment/{id}")
    public ResponseResult<Object> service(@PathVariable int id){
        return loanService.service(id);
    }

    //逾期
    @GetMapping("overdue/{id}")
    public ResponseResult<Object> overdue(@PathVariable int id){
        return loanService.overdue(id);
    }
}
