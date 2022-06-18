package com.woniuxy.user.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.user.dao
 * @Author qfx
 * @CreateTime 2022-06-18  10:20
 * @Description TODO
 * @Version 1.0
 */
@Mapper
public interface SupplyDao {
     int add(int corpID) ;
}
