package com.woniuxy.user.dao;

import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.entity.ScfpMenu;
import com.woniuxy.user.entity.ScfpRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.dao
 * @className: MenuDao
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/8 11:58
 * @version: 1.0
 */
@Mapper
public interface MenuDao {
    List<ScfpMenu> findAll();

    List<ScfpMenu> findChild(int id);

    int roleMenu(ScfpRoleMenu roleMenu);

    void delete(ScfpRoleMenu roleMenu);

    List<Integer> findMenuById(int role_id);
}
