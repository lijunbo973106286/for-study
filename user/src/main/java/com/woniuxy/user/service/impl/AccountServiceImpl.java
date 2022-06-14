package com.woniuxy.user.service.impl;

import com.woniuxy.user.dao.AccountDao;
import com.woniuxy.user.entity.ResStatus;
import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.entity.ScfpUser;
import com.woniuxy.user.entity.UserDTO;
import com.woniuxy.user.service.AccountService;
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
public class AccountServiceImpl implements AccountService {
    @Resource
    AccountDao accountDao;

    @Override
    @Transactional
    public ResponseResult newsub(UserDTO user) {
        return accountDao.newsub(user) > 0 && accountDao.newsubrole(user)  > 0 ? new ResponseResult(200, "操作成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "操作失败", null, ResStatus.FAIL);
    }

    @Override
    public ResponseResult delsub(int id) {
        return accountDao.delsub(id) > 0 ? new ResponseResult(200, "操作成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "操作失败", null, ResStatus.FAIL);
    }

    @Override
    @Transactional
    public ResponseResult modsub(UserDTO user) {
        if (user.getRole_id() == 0){
                    return accountDao.delrole(user) > 0 && accountDao.modsub(user) > 0 ? new ResponseResult(200, "操作成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "操作失败", null, ResStatus.FAIL);

        }accountDao.delrole(user);
        return accountDao.inrole(user) > 0  && accountDao.modsub(user) > 0 ? new ResponseResult(200, "操作成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "操作失败", null, ResStatus.FAIL);
    }

    @Override
    public ResponseResult qrysub(UserDTO user) {
        return new ResponseResult(200, "查询成功",
                accountDao.qrysub(user), ResStatus.SUCCESS);
    }

    @Override
    public ScfpUser login(ScfpUser user) {
        return accountDao.login(user);
    }
}
