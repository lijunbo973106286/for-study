package com.woniuxy.loan.dao;

import com.woniuxy.commons.entity.ScfpLoan;

import java.math.BigDecimal;
import java.util.List;

public interface LoanDao {

    int add(ScfpLoan scfpLoan);

    int update(ScfpLoan scfpLoan);

    ScfpLoan findByRepay_time(String repay_time);


    List<ScfpLoan> search(ScfpLoan scfpLoan);

    ScfpLoan getPrincipalData(int id);

    ScfpLoan getServiceData(int id);

    ScfpLoan getInterestData(int id);

    ScfpLoan findById(int id);

    int updateSurplus(int id, BigDecimal surplus);
}
