package com.woniuxy.fiance.service;

import com.woniuxy.commons.entity.DTO.LoanDTO;
import com.woniuxy.commons.util.ResponseResult;

public interface settleService {
    ResponseResult findsettle(int currentPate, int pageSize, int id);

    ResponseResult Paid(int currentPate, int pageSize, int id);

    ResponseResult unpaid(int currentPate, int pageSize, int id);

    ResponseResult<LoanDTO> search(LoanDTO loanDTO);

    ResponseResult<LoanDTO> password(int id);

    ResponseResult<LoanDTO> upstatus(int id);

    void addservice();
}
