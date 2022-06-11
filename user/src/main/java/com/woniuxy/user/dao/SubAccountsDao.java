package com.woniuxy.user.dao;

import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.entity.ScfpUser;
import com.woniuxy.user.entity.ScfpUserRole;
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
public interface SubAccountsDao {
    int add(ScfpUser user);

    List<ScfpUser> findByFid(int fid);

    int delete(int id);

    int statusChange(int id, String status);

    int update(ScfpUser user);

    List<ScfpUser> search(ScfpUser user);

    void remove(ScfpUserRole userRole);

    int userRole(ScfpUserRole userRole);

    List<Integer> findRoleById(int user_id);

}
