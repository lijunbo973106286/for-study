package com.woniuxy.supply.service;

import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpAmount;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.supply.service
 * @Author qfx
 * @CreateTime 2022-06-08  14:16
 * @Description TODO
 * @Version 1.0
 */
public interface BankService {
    ResponseResult findAllBank();

    ResponseResult allAmount(ScfpAmount scfpAmount);

    ResponseResult findTotal(ScfpAmount scfpAmount);

    ResponseResult findBy(ScfpAmount scfpAmount);

    ResponseResult credit(int eid);
}
