package com.woniuxy.chain.service;

import com.woniuxy.commons.entity.PageInfomation;
import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpChain;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-07 10:30
 * \* @Description：
 */
public interface ScfpChainService {
    ResponseResult<Object> update(ScfpChain scfpChain);

    ResponseResult<Object> insert(ScfpChain scfpChain);

    ResponseResult<Object> delete(int id);

    ResponseResult<ScfpChain> search(ScfpChain scfpChain);

    ResponseResult<Object> findAll(PageInfomation pageInfomation);
}
