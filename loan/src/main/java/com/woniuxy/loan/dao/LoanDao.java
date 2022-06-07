package com.woniuxy.loan.dao;

import com.woniuxy.commons.entity.ScfpLoan;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoanDao {
    int add(ScfpLoan scfpLoan);
}
