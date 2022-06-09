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
        if (all.isEmpty()) {
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        } else {
            PageInfo<ScfpChain> info = PageInfo.of(all);
            return new ResponseResult(200, "查询成功", info, ResStatus.SUCCESS);
        }
    }

    @Override
    public List<ScfpChain> findAllExcel() {
        return scfpChainDao.findAllExcel();
    }

    @Override
    public ResponseResult<Object> findCount(String status) {
        return new ResponseResult<>(200, "执行成功", scfpChainDao.findCount(status), ResStatus.SUCCESS);
    }

    @Override
    public ResponseResult<Object> findAllCount() {
        return new ResponseResult<>(200, "执行成功", scfpChainDao.findAllCount(), ResStatus.SUCCESS);
    }

    @Override
    public ResponseResult<ScfpEnterprise> getEnterprise(String bankName) {

        return new ResponseResult<ScfpEnterprise>(200, "执行成功", scfpChainDao.getEnterprise(bankName), ResStatus.SUCCESS);
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
