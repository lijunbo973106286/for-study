package com.woniuxy.loan.dao;

import com.woniuxy.commons.entity.ScfpLoan;

import java.util.List;

public interface LoanDao {

    int add(ScfpLoan scfpLoan);

    int update(ScfpLoan scfpLoan);

    ScfpLoan findByRepay_time(String repay_time);

    List<ScfpLoan> getAllByEnterpriseId(int id);
}
