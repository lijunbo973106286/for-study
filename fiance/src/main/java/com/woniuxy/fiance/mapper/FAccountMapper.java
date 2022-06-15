package com.woniuxy.fiance.mapper;

import com.woniuxy.commons.entity.ScfpFundAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface FAccountMapper {
    int add(ScfpFundAccount scfpFundAccount);

    int update(@Param("pay_pass") int pay_pass,@Param("id") int id);

    int del(int id);

    List<ScfpFundAccount> findID(int id);

    List<ScfpFundAccount> findAll();

    int activation(@Param("eid") int eid,@Param("faccount") String faccount);

    int freeze(int id);
}
