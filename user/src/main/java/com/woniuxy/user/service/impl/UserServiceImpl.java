package com.woniuxy.user.service.impl;

import com.woniuxy.user.dao.UserDao;
import com.woniuxy.user.entity.ResStatus;
import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.entity.ScfpUser;
import com.woniuxy.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.service.impl
 * @className: UserServiceImpl
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/9 17:54
 * @version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;
    @Override
    public ResponseResult register(ScfpUser user) {
        return userDao.register(user) > 0 ? new ResponseResult(200,"注册成功",null, ResStatus.SUCCESS):new ResponseResult(200,"注册失败",null, ResStatus.FAIL);
    }
}