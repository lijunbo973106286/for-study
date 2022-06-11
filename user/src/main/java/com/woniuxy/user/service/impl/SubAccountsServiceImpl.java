package com.woniuxy.user.service.impl;

import com.woniuxy.user.dao.SubAccountsDao;
import com.woniuxy.user.entity.*;
import com.woniuxy.user.service.SubAccountsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.service.impl
 * @className: SubAccountServiceImpl
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/7 15:04
 * @version: 1.0
 */
@Service
public class SubAccountsServiceImpl implements SubAccountsService {
    @Resource
    SubAccountsDao subAccountsDao;

    @Override
    public ResponseResult add(ScfpUser user) {
        return subAccountsDao.add(user) > 0 ? new ResponseResult(200, "添加成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "添加失败", null, ResStatus.FAIL);
    }

    @Override
    public ResponseResult findByFid(int fid) {
        return new ResponseResult(200, "查询成功",
                subAccountsDao.findByFid(fid),
                ResStatus.SUCCESS);
    }

    @Override
    public ResponseResult delete(int id) {
        return subAccountsDao.delete(id) > 0 ? new ResponseResult(200, "删除成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "删除失败", null, ResStatus.FAIL);
    }

    @Override
    public ResponseResult statusChange(int id, String status) {
        return subAccountsDao.statusChange(id, status) > 0 ? new ResponseResult(200, "状态更改成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "状态更改失败", null, ResStatus.FAIL);
    }

    @Override
    public ResponseResult update(ScfpUser user) {
        return subAccountsDao.update(user) > 0 ? new ResponseResult(200, "资料更改成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "资料更改失败", null, ResStatus.FAIL);
    }

    @Override
    public ResponseResult search(ScfpUser user) {
        return new ResponseResult(200, "查询成功",
                subAccountsDao.search(user),
                ResStatus.SUCCESS);
    }

    @Override
    @Transactional
    public ResponseResult userRole(ScfpUserRole userRole) {
        if (userRole.getRole_id().size() > 0) {
            subAccountsDao.remove(userRole);
            return subAccountsDao.userRole(userRole) > 0 ? new ResponseResult(200, "角色修改成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "角色修改失败", null, ResStatus.FAIL);
        } else {
            subAccountsDao.remove(userRole);
            return new ResponseResult(200, "角色已清空", null, ResStatus.SUCCESS);
        }
    }

    @Override
    public ResponseResult findRoleById(int user_id) {
        return new ResponseResult(200, "查询成功",
                new ScfpUserRole(
                        user_id, subAccountsDao.findRoleById(user_id)
                ), ResStatus.SUCCESS);
    }

    @Override
    public ResponseResult<Object> findUserById(int id) {
        ScfpUser user = subAccountsDao.findUserById(id);
        return new ResponseResult<>(200,"查询成功",user,ResStatus.SUCCESS);
    }
}
