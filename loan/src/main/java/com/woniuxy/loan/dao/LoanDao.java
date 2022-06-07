package com.woniuxy.loan.dao;

import com.woniuxy.commons.entity.ScfpLoan;

public interface LoanDao {

    int add(ScfpLoan scfpLoan);

    int update(ScfpLoan scfpLoan);

    ScfpLoan findById(int id);
}
