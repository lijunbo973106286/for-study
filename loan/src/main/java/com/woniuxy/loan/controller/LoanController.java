package com.woniuxy.loan.controller;

import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpLoan;
import com.woniuxy.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    public ResponseResult<Object> applyLoan(@RequestBody ScfpLoan scfpLoan) {
        return loanService.applyLoan(scfpLoan);
    }

    //还款
    @GetMapping("/repayment/{id}")
    public ResponseResult<Object> repayment(@PathVariable("id") int id){
        return loanService.repayment(id);
    }

    //支付利息
    @GetMapping("/interest_payment/{id}")
    public ResponseResult<Object> interest(@PathVariable("id") int id){
        return loanService.interest(id);
    }

    //支付手续费
    @GetMapping("/service_payment/{id}")
    public ResponseResult<Object> service(@PathVariable("id") int id){
        return loanService.service(id);
    }

    //结清所有费用
    @GetMapping("/payAll/{id}")
    public ResponseResult<Object> payAll(@PathVariable("id") int id){
        return loanService.payAll(id);
    }

    //查询某用户公司的所有兑付记录
    @PostMapping("/search")
    public ResponseResult<Object> search(@RequestBody ScfpLoan scfpLoan){
        return loanService.search(scfpLoan);
    }

    //根据企业id查询未还款的数据：记录条数和总金额
    @GetMapping("/getPrincipalData/{id}")
    public  ResponseResult<ScfpLoan> getPrincipalData(@PathVariable("id") int id){
        return loanService.getPrincipalData(id);
    }

    //根据企业id查询未支付服务费的数据：记录条数和总金额
    @GetMapping("/getServiceData/{id}")
    public  ResponseResult<ScfpLoan> getServiceData(@PathVariable("id") int id){
        return loanService.getServiceData(id);
    }


    //根据企业id查询未支付利息的数据：记录条数和总金额
    @GetMapping("/getInterestData/{id}")
    public  ResponseResult<ScfpLoan> getInterestData(@PathVariable("id") int id){
        return loanService.getInterestData(id);
    }
}
