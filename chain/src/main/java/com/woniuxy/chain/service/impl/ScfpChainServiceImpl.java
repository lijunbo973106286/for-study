package com.woniuxy.chain.service.impl;

import com.woniuxy.chain.dao.ScfpChainDao;
import com.woniuxy.chain.service.ScfpChainService;
import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpChain;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
