package com.woniuxy.loan.service;

import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpLoan;

public interface LoanService {
    ResponseResult<Object> applyLoan(ScfpLoan scfpLoan);

    ResponseResult<Object> repayment(int id);

    ResponseResult<Object> interest(int id);

    ResponseResult<Object> service(int id);

    ResponseResult<Object> overdue(int id);
}
