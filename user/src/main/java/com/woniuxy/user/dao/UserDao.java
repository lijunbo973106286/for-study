package com.woniuxy.user.dao;

import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.entity.ScfpUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.dao
 * @className: UserDao
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/9 17:57
 * @version: 1.0
 */
@Mapper
public interface UserDao {
    int register(ScfpUser user);
}
