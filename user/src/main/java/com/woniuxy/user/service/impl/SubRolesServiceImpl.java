package com.woniuxy.user.service.impl;

import com.woniuxy.user.dao.SubRolesDao;
import com.woniuxy.user.entity.ResStatus;
import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.entity.ScfpRole;
import com.woniuxy.user.service.SubRolesService;
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
public class SubRolesServiceImpl implements SubRolesService {
    @Resource
    SubRolesDao subRolesDao;

    @Override
    public ResponseResult create(ScfpRole role) {
        return subRolesDao.create(role) > 0 ? new ResponseResult(200, "角色创建成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "角色创建失败", null, ResStatus.FAIL);
    }

    @Override
    public ResponseResult delete(int id) {
        return subRolesDao.delete(id) > 0 ? new ResponseResult(200, "角色删除成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "角色删除失败", null, ResStatus.FAIL);
    }

    @Override
    public ResponseResult update(ScfpRole role) {
        return subRolesDao.update(role) > 0 ? new ResponseResult(200, "角色修改成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "角色修改失败", null, ResStatus.FAIL);
    }

    @Override
    public ResponseResult findAll() {
        return new ResponseResult(200, "查询成功",
                subRolesDao.findAll(),
                ResStatus.SUCCESS);
    }

    @Override
    public ResponseResult statusChange(int id, String status) {
        return subRolesDao.statusChange(id, status) > 0 ? new ResponseResult(200, "状态修改成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "状态修改失败", null, ResStatus.FAIL);
    }
}
