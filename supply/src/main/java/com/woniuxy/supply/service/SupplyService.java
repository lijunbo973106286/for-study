package com.woniuxy.supply.service;

import com.woniuxy.commons.entity.DTO.SupplyDTO;
import com.woniuxy.commons.entity.PageInfomation;
import com.woniuxy.commons.entity.ResponseResult;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.supply.service
 * @Author qfx
 * @CreateTime 2022-06-07  11:16
 * @Description TODO
 * @Version 1.0
 */
public interface SupplyService {
    ResponseResult findAllSupply(PageInfomation pageInfomation);

    ResponseResult findById(int coreId);

    ResponseResult findByCondition(SupplyDTO supplyDTO);

    ResponseResult findByEid(SupplyDTO supplyDTO);

    ResponseResult findByConditionAndEid(SupplyDTO supplyDTO);

    ResponseResult findAllEnterprises(SupplyDTO supplyDTO);

    ResponseResult add(SupplyDTO supplyDTO);
}
