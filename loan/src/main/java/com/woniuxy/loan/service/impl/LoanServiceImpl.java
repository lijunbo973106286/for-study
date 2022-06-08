package com.woniuxy.loan.service.impl;

import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpLoan;
import com.woniuxy.loan.dao.LoanDao;
import com.woniuxy.loan.rabbitmq.producer.RepaymentProducer;
import com.woniuxy.loan.service.LoanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    private LoanDao loanDao;

    @Resource
    private RepaymentProducer repaymentProducer;

    /** 兑付：
     *  1. 查询链单信息判断是否过期，余额是否充足
     *  2. 确定还款时间，发布定时任务，逾期调用接口
     *  3. 获取服务费率和利率，计算封装进对象
     *  4. 调用dao接口增加兑付记录，并且调用openfeign接口修改链单余额
     */
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
            String repay_time = dateFormat.format(new Date(now.getTime() + 50000));
            scfpLoan.setRepay_time(repay_time);
            scfpLoan.setPrincipal_status("未还款");
            repaymentProducer.produce(50000,repay_time);

            //计算服务费，假设服务费为0.01%
            BigDecimal service = scfpLoan.getMoney().multiply(new BigDecimal("0.0001"));
            scfpLoan.setService(service);
            scfpLoan.setService_status("未支付");

            //计算利息,假设利息为1%
            BigDecimal interest = scfpLoan.getMoney().multiply(new BigDecimal("0.01"));
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

    /** 还款 */
    @Override
    public ResponseResult<Object> repayment(int id) {
        try {
            ScfpLoan scfpLoan = new ScfpLoan();
            scfpLoan.setId(id);
            scfpLoan.setPrincipal_status("已还款");
            int num = loanDao.update(scfpLoan);
            if (num>0){
                return ResponseResult.SUCCESS;
            }else {
                return ResponseResult.FAIL;
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.FAIL;
        }
    }

    /** 支付利息 */
    @Override
    public ResponseResult<Object> interest(int id) {
        try {
            ScfpLoan scfpLoan = new ScfpLoan();
            scfpLoan.setId(id);
            scfpLoan.setInterest_status("已支付");
            int num = loanDao.update(scfpLoan);
            if (num>0){
                return ResponseResult.SUCCESS;
            }else {
                return ResponseResult.FAIL;
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.FAIL;
        }
    }

    /** 支付手续费 */
    @Override
    public ResponseResult<Object> service(int id) {
        try {
            ScfpLoan scfpLoan = new ScfpLoan();
            scfpLoan.setId(id);
            scfpLoan.setService_status("已支付");
            int num = loanDao.update(scfpLoan);
            if (num>0){
                return ResponseResult.SUCCESS;
            }else {
                return ResponseResult.FAIL;
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.FAIL;
        }
    }

    /** 逾期：
     *  1. 根据id查询到相应的兑付记录（？逾期是否有对应的方案：费用和利息的增加？）
     *  2. 判断本金是否已还
     *  3. 增加利息（倘若逾期之前原利息已支付，如果更新额外的利息和服务费）
     *  4. 增加手续费
     *  5. 修改本金状态为逾期
     */
    @Override
    public ResponseResult<Object> overdue(String repay_time) {
        try{
            ScfpLoan scfpLoan = loanDao.findByRepay_time(repay_time);
            System.out.println(scfpLoan);
            if("已还款".equals(scfpLoan.getPrincipal_status())){
                return ResponseResult.SUCCESS;
            }
            scfpLoan.setInterest(scfpLoan.getInterest().multiply(new BigDecimal("1.5")));
            scfpLoan.setService(scfpLoan.getService().multiply(new BigDecimal("1.5")));
            scfpLoan.setPrincipal_status("已逾期");
            int num = loanDao.update(scfpLoan);
            if (num>0){
                return ResponseResult.SUCCESS;
            }else {
                return ResponseResult.FAIL;
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.FAIL;
        }
    }
}
