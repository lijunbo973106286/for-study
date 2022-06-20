package com.woniuxy.loan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniuxy.commons.entity.ResStatus;
import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpChain;
import com.woniuxy.commons.entity.ScfpLoan;
import com.woniuxy.commons.service.ScfpChainService;
import com.woniuxy.loan.dao.LoanDao;
import com.woniuxy.loan.rabbitmq.producer.RepaymentProducer;
import com.woniuxy.loan.service.LoanService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: supply-chain-finance
 * @description: 放款相关业务操作
 * @author: Linqiao Dai
 * @create: 2022-06-07 12:04
 **/
@Service
public class LoanServiceImpl implements LoanService {

    private static final Log log = LogFactory.getLog(LoanServiceImpl.class);

    @Resource
    private LoanDao loanDao;

    @Resource
    private RepaymentProducer repaymentProducer;

    @Resource
    private ScfpChainService scfpChainService;


    /** 兑付：
     *  1. 查询链单信息判断是否过期，余额是否充足
     *  2. 确定还款时间，发布定时任务，逾期调用接口
     *  3. 获取服务费率和利率，计算封装进对象
     *  4. 调用dao接口增加兑付记录，并且调用openfeign接口修改链单余额
     */
    @Override
    public ResponseResult<Object> applyLoan(ScfpLoan scfpLoan) {
        Date now = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            log.info("收到的链单id为："+scfpLoan.getChain_id());
            if (scfpLoan.getChain_id() == 0){
                return new ResponseResult<>(500, "链单信息错误", null, ResStatus.FAIL);
            }
            ScfpChain scfpChain = new ScfpChain();
            scfpChain.setId(scfpLoan.getChain_id());
            //调用链单微服务接口查询对应链单信息
            ResponseResult<ScfpChain> search = scfpChainService.findById(scfpChain);
            ScfpChain chain = (ScfpChain) search.getData();
            log.info("查询到的链单信息为："+chain);
            //链单是否过期，过期返回false
            Date old = dateFormat.parse(chain.getDeadline());
            if (old.getTime()<now.getTime()){
                return new ResponseResult<>(500, "链单已过期", null, ResStatus.FAIL);
            }
            //链单余额是否充足，不够返回false
            if (chain.getSurplus() == null||chain.getSurplus().compareTo(new BigDecimal(0))<1) {
                return new ResponseResult<>(500, "链单余额不足", null, ResStatus.FAIL);
            }
            //赋值
            scfpLoan.setEnterprise_id(chain.getEnterprise_id());
            scfpLoan.setMoney(chain.getSurplus());

            //获取当前时间
            String loan_time = dateFormat.format(now);
            scfpLoan.setLoan_time(loan_time);

            //假设还款时间为2个月
            String repay_time = dateFormat.format(new Date(now.getTime() + 60 * 24 * 60 * 60 * 1000L));
            scfpLoan.setRepay_time(repay_time);
            scfpLoan.setPrincipal_status("未还款");
            repaymentProducer.produce(60 * 24 * 60 * 60 * 1000L,repay_time);

            //计算服务费，假设服务费为0.01%
            BigDecimal service = scfpLoan.getMoney().multiply(new BigDecimal("0.0001"));
            scfpLoan.setService(service);
            scfpLoan.setService_status("未支付");

            //计算利息,假设利息为1%
            BigDecimal interest = scfpLoan.getMoney().multiply(new BigDecimal("0.01"));
            scfpLoan.setInterest(interest);
            scfpLoan.setInterest_status("未支付");

            //调用链单微服务，修改余额
            ResponseResult<Object> result = scfpChainService.updateLoan(chain.getId());
            if (result.getCode() != 200) {
                return ResponseResult.FAIL;
            }

            //调用dao接口添加放款信息
            int num = loanDao.add(scfpLoan);

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
            ScfpLoan result = loanDao.findById(id);
            int enterprise_id = loanDao.findEnterprise_id(result.getChain_id());
            int c = loanDao.updateSurplus(enterprise_id,result.getMoney());
            if (c<1){
                return ResponseResult.FAIL;
            }
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


    /**
     * 查询所有的兑付记录
     */
    @Override
    public ResponseResult<Object> search(ScfpLoan scfpLoan) {
        PageHelper.startPage(scfpLoan.getCurrentPage(), scfpLoan.getPageSize());
        List<ScfpLoan> all = loanDao.search(scfpLoan);
        PageInfo<ScfpLoan> list = new PageInfo<>(all);
        ResponseResult<Object> responseResult = new ResponseResult<>();
        responseResult.setCode(200);
        responseResult.setData(list);
        responseResult.setMessage("查询成功");
        return responseResult;
    }

    @Override
    public ResponseResult<ScfpLoan> getPrincipalData(int id) {
        ScfpLoan scfpLoan = loanDao.getPrincipalData(id);
        ResponseResult<ScfpLoan> responseResult;
        responseResult = new ResponseResult<>();
        responseResult.setData(scfpLoan);
        return responseResult;
    }

    @Override
    public ResponseResult<ScfpLoan> getServiceData(int id) {
        ScfpLoan scfpLoan = loanDao.getServiceData(id);
        ResponseResult<ScfpLoan> responseResult;
        responseResult = new ResponseResult<>();
        responseResult.setData(scfpLoan);
        return responseResult;
    }

    @Override
    public ResponseResult<ScfpLoan> getInterestData(int id) {
        ScfpLoan scfpLoan = loanDao.getInterestData(id);
        ResponseResult<ScfpLoan> responseResult;
        responseResult = new ResponseResult<>();
        responseResult.setData(scfpLoan);
        return responseResult;
    }

    @Override
    public ResponseResult<Object> payAll(int id) {
        try {
            ScfpLoan scfpLoan = new ScfpLoan();
            scfpLoan.setId(id);
            scfpLoan.setInterest_status("已支付");
            scfpLoan.setPrincipal_status("已还款");
            scfpLoan.setService_status("已支付");
            ScfpLoan result = loanDao.findById(id);
            int enterprise_id = loanDao.findEnterprise_id(result.getChain_id());
            int c = loanDao.updateSurplus(enterprise_id,result.getMoney());
            if (c<1){
                return ResponseResult.FAIL;
            }
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
