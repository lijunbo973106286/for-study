package com.woniuxy.user.dao;

import com.woniuxy.user.entity.ScfpMenu;
import com.woniuxy.user.entity.ScfpRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    List<ScfpMenu> all();

    List<ScfpMenu> findChild(int id);

    List<ScfpMenu> getList(int id);

    List<ScfpMenu> getChild(@Param("fid") int fid, @Param("user_id") int user_id);

    List<Integer> getRM(int id);

    int delRM(ScfpRoleMenu roleMenu);

    int setRM(ScfpRoleMenu roleMenu);

    List<Integer> getFM(ScfpRoleMenu roleMenu);
}
