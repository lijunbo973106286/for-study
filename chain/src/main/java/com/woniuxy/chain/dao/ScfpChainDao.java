package com.woniuxy.chain.dao;

import com.woniuxy.commons.entity.ScfpChain;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-07 10:34
 * \* @Description：
 */
public interface ScfpChainDao {
    int update(ScfpChain scfpChain);

    int insert(ScfpChain scfpChain);
}
