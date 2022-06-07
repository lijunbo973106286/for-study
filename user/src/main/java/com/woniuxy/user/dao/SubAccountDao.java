package com.woniuxy.user.dao;

import com.woniuxy.user.entity.ScfpUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.dao
 * @className: SubAccountDao
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/7 15:05
 * @version: 1.0
 */
@Mapper
public interface SubAccountDao {
    int add(ScfpUser user);
}
