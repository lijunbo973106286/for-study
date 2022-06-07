package com.woniuxy.supply.service;

import com.woniuxy.commons.entity.DTO.NetworkDTO;
import com.woniuxy.commons.entity.PageInfomation;
import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpNetwork;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.supply.service
 * @Author qfx
 * @CreateTime 2022-06-07  16:13
 * @Description TODO
 * @Version 1.0
 */
public interface NetworkService {
    ResponseResult findAllNetwork(PageInfomation pageInfomation);

    ResponseResult addNetwork(NetworkDTO networkDTO);

    ResponseResult updateNetwork(NetworkDTO networkDTO);

    ResponseResult findByCondition(NetworkDTO networkDTO);
}
