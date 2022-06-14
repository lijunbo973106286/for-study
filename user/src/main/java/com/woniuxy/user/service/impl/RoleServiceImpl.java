package com.woniuxy.user.service.impl;

import com.woniuxy.user.dao.RoleDao;
import com.woniuxy.user.entity.ResStatus;
import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.entity.RoleDTO;
import com.woniuxy.user.entity.ScfpRole;
import com.woniuxy.user.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.service.impl
 * @className: SubRolesServiceImpl
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/7 20:47
 * @version: 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleDao roleDao;

    @Override
    public ResponseResult newrole(ScfpRole role) {
        return roleDao.newrole(role) > 0 ? new ResponseResult(200, "操作成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "操作失败", null, ResStatus.FAIL);
    }

    @Override
    public ResponseResult delrole(int id) {
        return roleDao.delrole(id) > 0 && roleDao.delRM(id) > 0 && roleDao.delUR(id) > 0 ? new ResponseResult(200, "操作成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "操作失败", null, ResStatus.FAIL);
    }

    @Override
    public ResponseResult modrole(ScfpRole role) {
        return roleDao.modrole(role) > 0 ? new ResponseResult(200, "操作成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "操作失败", null, ResStatus.FAIL);
    }

    @Override
    public ResponseResult qryrole(RoleDTO role) {
        return new ResponseResult(200, "查询成功",
                roleDao.qryrole(role), ResStatus.SUCCESS);
    }
}
