package com.woniuxy.chain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniuxy.chain.dao.ScfpChainDao;
import com.woniuxy.chain.service.ScfpChainService;
import com.woniuxy.commons.entity.*;
import com.woniuxy.commons.entity.ResStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-07 10:33
 * \* @Description：
 */
@Service
public class ScfpChainServiceImpl implements ScfpChainService {

    @Resource
    ScfpChainDao scfpChainDao;

    @Override
    public ResponseResult<Object> update(ScfpChain scfpChain) {
        int i = scfpChainDao.update(scfpChain);
        if (i > 0) {
            return ResponseResult.SUCCESS;
        } else {
            return ResponseResult.FAIL;
        }
    }

    @Override
    public ResponseResult<Object> insert(ScfpChain scfpChain) {
        int i = scfpChainDao.insert(scfpChain);
        if (i > 0) {
            return ResponseResult.SUCCESS;
        } else {
            return ResponseResult.FAIL;
        }
    }

    @Override
    public ResponseResult<Object> findAll(ScfpChain scfpChain) {
        int currentPage = scfpChain.getCurrentPage();
        int pageSize = scfpChain.getPageSize();
        PageHelper.startPage(currentPage, pageSize);
        List<ScfpChain> all = scfpChainDao.findAll(scfpChain);
        System.out.println(all.get(0).getScfpNetwork());
        if (all.isEmpty()) {
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        } else {
            PageInfo<ScfpChain> info = PageInfo.of(all);
            return new ResponseResult(200, "查询成功", info, ResStatus.SUCCESS);
        }
    }

    @Override
    public List<ScfpChain> findAllExcel(int eid) {
        return scfpChainDao.findAllExcel(eid);
    }

    @Override
    public ResponseResult<Object> findCount(ScfpChain scfpChain) {
        return new ResponseResult<>(200, "执行成功", scfpChainDao.findCount(scfpChain), ResStatus.SUCCESS);
    }

    @Override
    public ResponseResult<Object> findAllCount(int eid) {
        return new ResponseResult<>(200, "执行成功", scfpChainDao.findAllCount(eid), ResStatus.SUCCESS);
    }

    @Override
    public ResponseResult<Object> updateLoan(int chain_id) {
        ScfpChain scfpChain = new ScfpChain();
        scfpChain.setId(chain_id);
        scfpChain.setStatus(19);
        scfpChain.setSurplus(BigDecimal.valueOf(0.0001));
        int i = scfpChainDao.update(scfpChain);
        if (i > 0) {
            return ResponseResult.SUCCESS;
        } else {
            return ResponseResult.FAIL;
        }
    }

    @Override
    public ResponseResult<Object> updateBatLoan(List<Integer> ids) {
        ScfpChain scfpChain = new ScfpChain();
        scfpChain.setStatus(19);
        scfpChain.setSurplus(BigDecimal.valueOf(0.0001));
        int count = 0;
        for (int id : ids){
            scfpChain.setId(id);
            int i = scfpChainDao.update(scfpChain);
            if (i > 0){
                count++;
            }
        }
        if (count == ids.size()){
            return ResponseResult.SUCCESS;
        } else {
            return ResponseResult.FAIL;
        }
    }

    @Override
    public ResponseResult<Object> checkPayPass(ScfpEnterprise scfpEnterprise) {
        if (scfpChainDao.checkPayPass(scfpEnterprise) != null) {
            return ResponseResult.SUCCESS;
        } else {
            return ResponseResult.FAIL;
        }
    }

    @Override
    public ResponseResult<ScfpEnterprise> getEnterprise(String bankName) {
        return new ResponseResult<ScfpEnterprise>(200, "执行成功", scfpChainDao.getEnterprise(bankName), ResStatus.SUCCESS);
    }

    @Override
    public ResponseResult<ScfpEnterprise> findAllLoan(ScfpChain scfpChain) {
        int currentPage = scfpChain.getCurrentPage();
        int pageSize = scfpChain.getPageSize();
        PageHelper.startPage(currentPage, pageSize);
        List<ScfpChain> all = scfpChainDao.findAllLoan(scfpChain);
        if (all.isEmpty()) {
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        } else {
            PageInfo<ScfpChain> info = PageInfo.of(all);
            return new ResponseResult(200, "查询成功", info, ResStatus.SUCCESS);
        }
    }

    @Override
    public ResponseResult<Object> findLoanCount(ScfpChain scfpChain) {
        return new ResponseResult<>(200, "执行成功", scfpChainDao.findLoanCount(scfpChain), ResStatus.SUCCESS);
    }

    @Override
    public ResponseResult<ScfpEnterprise> findAllEnterprise() {
        List<ScfpEnterprise> all=scfpChainDao.findAllEnterprise();

        return new ResponseResult(200, "执行成功", all, ResStatus.SUCCESS);
    }

    @Override
    public ResponseResult<ScfpFund> findAllFund() {
        List<ScfpFund> all=scfpChainDao.findAllFund();

        return new ResponseResult(200, "执行成功", all, ResStatus.SUCCESS);
    }

    @Override
    public ResponseResult<Object> findStatus(int cs_id) {
        return new ResponseResult<>(200, "执行成功", scfpChainDao.findStatus(cs_id), ResStatus.SUCCESS);
    }

    @Override
    public ResponseResult<ScfpChain> findById(ScfpChain scfpChain) {
        return new ResponseResult<>(200, "执行成功", scfpChainDao.findById(scfpChain), ResStatus.SUCCESS);
    }

    @Override
    public ResponseResult<ScfpEnterprise> findEnterprise() {
        List<ScfpEnterprise> all=scfpChainDao.findEnterprise();

        return new ResponseResult(200, "执行成功", all, ResStatus.SUCCESS);
    }

    @Override
    public ResponseResult<Object> delete(int id) {
        int i = scfpChainDao.delete(id);
        if (i > 0) {
            return ResponseResult.SUCCESS;
        } else {
            return ResponseResult.FAIL;
        }
    }

    @Override
    public ResponseResult search(ScfpChain scfpChain) {
        PageHelper.startPage(scfpChain.getCurrentPage(), scfpChain.getPageSize());
        List<ScfpChain> all = scfpChainDao.search(scfpChain);
        PageInfo<ScfpChain> pageInfo = PageInfo.of(all);
        return new ResponseResult<>(200, "查询成功", pageInfo, ResStatus.SUCCESS);
    }
}
