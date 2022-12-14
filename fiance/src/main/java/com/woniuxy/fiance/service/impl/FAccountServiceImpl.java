package com.woniuxy.fiance.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniuxy.commons.entity.ScfpFundAccount;
import com.woniuxy.commons.util.ResponseResult;
import com.woniuxy.fiance.mapper.FAccountMapper;
import com.woniuxy.fiance.service.FAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class FAccountServiceImpl implements FAccountService {
  ResponseResult responseResult=  ResponseResult.getResponseResult();
    @Resource
    FAccountMapper fAccountMapper;
    @Override
    public ResponseResult add(ScfpFundAccount scfpFundAccount) {
        List<ScfpFundAccount> list=fAccountMapper.findID(scfpFundAccount.getEid());
        if (list.size()==0){
            int i=  fAccountMapper.add(scfpFundAccount);
            if (i!=1){
                return responseResult.fail();
            }
            return responseResult.success(i);
        }
        responseResult.setMsg("已经有一个帐号了");
        return responseResult;

    }
    //修改资金账户
    @Override
    public ResponseResult update(int pay_pass, int id) {
       int i =fAccountMapper.update(pay_pass,id);
        if (i!=1){
            return responseResult.fail();
        }
        return responseResult.success(i);

    }

    @Override
    public ResponseResult del(int id) {
       int i= fAccountMapper.del(id);
        if (i!=1){
            return responseResult.fail();
        }
        return responseResult.success(i);
    }

    @Override
    public ResponseResult findID(int id) {
        List<ScfpFundAccount> scfpFundAccount=fAccountMapper.findID(id);
        if (scfpFundAccount != null){
         return responseResult.success(scfpFundAccount);
      }
      return responseResult.fail();
    }

    @Override
    public ResponseResult findAll(int currentPate, int pageSize) {
        PageHelper.startPage(currentPate, pageSize);
       List<ScfpFundAccount> scfpFundAccounts= fAccountMapper.findAll();
       if (scfpFundAccounts!=null){
           PageInfo<ScfpFundAccount> list = PageInfo.of(scfpFundAccounts);
           return responseResult.success(list);
       }
        return responseResult.fail();
    }
    @Transactional
    @Override
    public ResponseResult activation(int id) {
        //已激活 直接返回
        List<ScfpFundAccount> list = fAccountMapper.findID(id);
        ScfpFundAccount scfpFundAccount = list.get(0);
        String faccount1 = scfpFundAccount.getFaccount();
        System.out.println(faccount1);
        if (faccount1 !=null ){
            responseResult.setMsg("已激活");
            responseResult.setCode(500);
            return responseResult;
        }
        //生成资金账户编号
        Date date=new Date();
        long time = date.getTime();
        String s=time+"";
        String substring = s.substring(0, 4);
        Random random=new Random();
        String i=  random.nextInt(100000)+ 1+"";
        String faccount="HK"+i+substring;
        //修改开通状态
        int j=fAccountMapper.activation(id,faccount);
        if (j!=0){
            return responseResult.success(j);
        }
        return responseResult.fail();
    }

    @Override
    public ResponseResult freeze(int id) {
       int i= fAccountMapper.freeze(id);
        if (i!=1){
            responseResult.fail();
        }
        return responseResult.success(i);
    }

    @Override
    public ResponseResult pay(ScfpFundAccount account) {
        List<ScfpFundAccount> scfpFundAccount = fAccountMapper.findID(account.getEid());
        if (scfpFundAccount.size() != 1 || !"已开通".equals(scfpFundAccount.get(0).getStatus())) {
            return responseResult.success("账户异常");
        }
        if (scfpFundAccount.get(0).getPay_pass() != account.getPay_pass()){
            return responseResult.success("密码错误");
        }
        if(scfpFundAccount.get(0).getResidual().compareTo(account.getResidual()) < 0){
            return responseResult.success("余额不足");
        }
        int j = fAccountMapper.pay(account);
        if (j != 1) {
            responseResult.success("支付失败");
        }
        return responseResult.success(j);
    }
}
