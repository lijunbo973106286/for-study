package com.woniuxy.supply.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniuxy.commons.entity.DTO.NetworkDTO;
import com.woniuxy.commons.entity.DTO.SupplyDTO;
import com.woniuxy.commons.entity.ResStatus;
import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpAmount;
import com.woniuxy.commons.entity.ScfpEnterprise;
import com.woniuxy.supply.dao.BankDao;
import com.woniuxy.supply.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.supply.service.impl
 * @Author qfx
 * @CreateTime 2022-06-08  14:16
 * @Description TODO
 * @Version 1.0
 */
@Slf4j
@Service
public class BankServiceImpl implements BankService {
    @Resource
    BankDao bankDao;

    @Override
    public ResponseResult findAllBank() {
        List<ScfpEnterprise> all = bankDao.findAllBank();
        if (all.isEmpty()) {
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        } else {
            log.info("所有资金方：{}", all);
            return new ResponseResult(200, "查询成功", all, ResStatus.SUCCESS);
        }
    }

    @Override
    public ResponseResult allAmount(ScfpAmount scfpAmount) {
        int currentPage = scfpAmount.getCurrentPage();
        int pageSize = scfpAmount.getPageSize();
        PageHelper.startPage(currentPage, pageSize);
        List<ScfpAmount> all = bankDao.allAmount(scfpAmount);
        if (all.isEmpty()) {
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        } else {
            PageInfo<ScfpAmount> info = PageInfo.of(all);
            return new ResponseResult(200, "查询成功", info, ResStatus.SUCCESS);
        }
    }

    @Override
    public ResponseResult findTotal(ScfpAmount scfpAmount) {
//        List<ScfpAmount> all = bankDao.allAmount(scfpAmount);
//        BigDecimal totalMoney = new BigDecimal(0);
//        BigDecimal totalAvailable = new BigDecimal(0);
//        if (all.isEmpty()) {
//            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
//        } else {
//            for (ScfpAmount amount : all
//            ) {
//                totalMoney = totalMoney.add(amount.getTotal());
//                totalAvailable = totalAvailable.add(amount.getAvailable());
//            }
//            scfpAmount.setTotalMoney(totalMoney);
//            scfpAmount.setTotalAvailable(totalAvailable);
//            return new ResponseResult(200, "查询成功", scfpAmount, ResStatus.SUCCESS);
//        }
        ScfpAmount scfpAmount_ = bankDao.findCredit(scfpAmount.getEid());
        if (scfpAmount_ == null) {
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        } else {
            return new ResponseResult(200, "查询成功", scfpAmount_, ResStatus.SUCCESS);
        }
    }

    @Override
    public ResponseResult findBy(ScfpAmount scfpAmount) {
        int currentPage = scfpAmount.getCurrentPage();
        int pageSize = scfpAmount.getPageSize();
        PageHelper.startPage(currentPage, pageSize);
        List<ScfpAmount> all = bankDao.findBy(scfpAmount);
        if (all.isEmpty()) {
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        } else {
            PageInfo<ScfpAmount> info = PageInfo.of(all);
            return new ResponseResult(200, "查询成功", info, ResStatus.SUCCESS);
        }
    }

    @Override
    public ResponseResult credit(int eid) {
        int i = bankDao.credit(eid);
        if (i <= 0) {
            return new ResponseResult(500, "授信失败", null, ResStatus.FAIL);
        } else {
            return new ResponseResult(200, "授信成功", null, ResStatus.SUCCESS);
        }
    }
}
