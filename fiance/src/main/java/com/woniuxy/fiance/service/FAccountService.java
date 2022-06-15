package com.woniuxy.fiance.service;

import com.woniuxy.commons.entity.ScfpFundAccount;
import com.woniuxy.commons.util.ResponseResult;

public interface FAccountService {
    ResponseResult add(ScfpFundAccount scfpFundAccount);

    ResponseResult update(int pay_pass,int id);

    ResponseResult del(int id);

    ResponseResult findID(int id);

    ResponseResult findAll(int currentPate, int pageSize);

    ResponseResult activation(int id);

    ResponseResult freeze(int id);
}
