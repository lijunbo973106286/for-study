package com.woniuxy.user.dao;

import com.woniuxy.user.entity.RoleDTO;
import com.woniuxy.user.entity.ScfpEnterpriseType;
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
public interface RoleDao {

    int newrole(ScfpRole role);

    int delrole(int id);

    int modrole(ScfpRole role);

    List<RoleDTO> qryrole(RoleDTO role);

    int delRM(int id);

    int delUR(int id);

    List<ScfpEnterpriseType> corpType();
}
