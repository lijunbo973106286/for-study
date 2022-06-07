package com.woniuxy.chain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniuxy.chain.dao.ScfpChainDao;
import com.woniuxy.chain.service.ScfpChainService;
import com.woniuxy.commons.entity.PageInfomation;
import com.woniuxy.commons.entity.ResStatus;
import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpChain;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public ResponseResult<Object> findAll(PageInfomation pageInfomation) {
        int currentPage = pageInfomation.getCurrentPage();
        int pageSize = pageInfomation.getPageSize();
        PageHelper.startPage(currentPage,pageSize);
        List<ScfpChain> all = scfpChainDao.findAll();
        if(all.isEmpty()){
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        }else{
            PageInfo<ScfpChain> info = PageInfo.of(all);
            return new ResponseResult(200, "查询成功", info, ResStatus.SUCCESS);
        }
    }
}
