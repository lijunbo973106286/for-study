package com.woniuxy.loan.service.impl;

import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpLoan;
import com.woniuxy.loan.dao.LoanDao;
import com.woniuxy.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: supply-chain-finance
 * @description: 放款相关业务操作
 * @author: Linqiao Dai
 * @create: 2022-06-07 12:04
 **/
@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanDao loanDao;

    @Override
    public ResponseResult<Object> applyLoan(ScfpLoan scfpLoan) {
        try {
            //调用链单微服务接口查询对应链单信息

            //链单是否过期，过期返回false

            //链单余额是否充足，不够返回false

            //获取当前时间
            Date now = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String loan_time = dateFormat.format(now);
            scfpLoan.setLoan_time(loan_time);

            //假设还款时间为2个月（是否需要定时任务？）
            String repay_time = dateFormat.format(new Date(now.getTime() + 60*24*60*60*1000L));
            scfpLoan.setRepay_time(repay_time);
            scfpLoan.setPrincipal_status("未还款");

            //计算服务费，假设服务费为0.01%
            BigDecimal service = scfpLoan.getMoney().multiply(new BigDecimal(0.0001));
            scfpLoan.setService(service);
            scfpLoan.setService_status("未支付");

            //计算利息,假设利息为1%
            BigDecimal interest = scfpLoan.getMoney().multiply(new BigDecimal(0.01));
            scfpLoan.setInterest(interest);
            scfpLoan.setInterest_status("未支付");

            //调用dao接口添加放款信息
            int num = loanDao.add(scfpLoan);

            //调用链单微服务，修改余额
            if (num>0){
                return ResponseResult.SUCCESS;
            }else{
                return ResponseResult.FAIL;
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.FAIL;
        }
    }
}
