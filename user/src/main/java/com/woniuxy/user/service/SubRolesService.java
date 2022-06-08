package com.woniuxy.user.service;

import com.woniuxy.user.entity.ResponseResult;
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
public interface SubRolesService {
    ResponseResult create(ScfpRole role);

    ResponseResult delete(int id);

    ResponseResult update(ScfpRole role);

    ResponseResult findAll();

    ResponseResult statusChange(int id, String status);
}
