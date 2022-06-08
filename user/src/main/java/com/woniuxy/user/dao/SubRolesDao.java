package com.woniuxy.user.dao;

import com.woniuxy.commons.util.ResponseResult;
import com.woniuxy.user.entity.ScfpRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.dao
 * @className: SubRolesDao
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/7 20:50
 * @version: 1.0
 */
@Mapper
public interface SubRolesDao {
    int create(ScfpRole role);

    int delete(int id);

    int update(ScfpRole role);

    List<ScfpRole> findAll();

    int statusChange(int id, String status);
}
