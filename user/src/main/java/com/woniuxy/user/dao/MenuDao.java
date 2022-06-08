package com.woniuxy.user.dao;

import com.woniuxy.commons.entity.ScfpMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.commons.dao
 * @Author qfx
 * @CreateTime 2022-06-08  09:47
 * @Description TODO
 * @Version 1.0
 */
@Mapper
public interface MenuDao {
    List<ScfpMenu> findAll();
}
