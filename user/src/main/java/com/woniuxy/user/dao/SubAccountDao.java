package com.woniuxy.user.dao;

import com.woniuxy.commons.util.ResponseResult;
import com.woniuxy.user.entity.ScfpUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    List<ScfpUser> findByFid(int fid);

    int delete(int id);

    int statusChange(int id, String status);

    int update(ScfpUser user);

    List<ScfpUser> search(ScfpUser user);
}
