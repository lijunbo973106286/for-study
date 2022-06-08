package com.woniuxy.user.service.impl;

import com.woniuxy.user.dao.UserDao;
import com.woniuxy.user.entity.ResStatus;
import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.service.impl
 * @className: UserServiceImpl
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/8 17:22
 * @version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;
}
