package com.woniuxy.loan.service;

import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpLoan;

public interface LoanService {
    ResponseResult<Object> applyLoan(ScfpLoan scfpLoan);

    ResponseResult<Object> repayment(int id);

    ResponseResult<Object> interest(int id);

    ResponseResult<Object> service(int id);

    ResponseResult<Object> overdue(String repay_time);

    ResponseResult<Object> search(ScfpLoan scfpLoan);

    ResponseResult<ScfpLoan> getPrincipalData(int id);

    ResponseResult<ScfpLoan> getServiceData(int id);

    ResponseResult<ScfpLoan> getInterestData(int id);

    ResponseResult<Object> payAll(int id);
}
