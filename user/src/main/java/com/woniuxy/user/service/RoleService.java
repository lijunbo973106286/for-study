package com.woniuxy.user.service;

import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.entity.RoleDTO;
import com.woniuxy.user.entity.ScfpRole;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.service
 * @className: SubRolesService
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/7 20:45
 * @version: 1.0
 */
public interface RoleService {

    ResponseResult newrole(ScfpRole role);

    ResponseResult delrole(int id);

    ResponseResult modrole(ScfpRole role);

    ResponseResult qryrole(RoleDTO role);
}

