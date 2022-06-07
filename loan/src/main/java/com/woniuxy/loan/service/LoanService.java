package com.woniuxy.loan.service;

import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpLoan;

public interface LoanService {
    ResponseResult<Object> applyLoan(ScfpLoan scfpLoan);
}
