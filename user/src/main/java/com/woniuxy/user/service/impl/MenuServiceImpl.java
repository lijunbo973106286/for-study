package com.woniuxy.user.service.impl;

import com.woniuxy.user.dao.MenuDao;
import com.woniuxy.user.entity.ResStatus;
import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.service.impl
 * @className: MenuServiceImpl
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/8 11:56
 * @version: 1.0
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    MenuDao menuDao;

    @Override
    public ResponseResult getList(int id) {
        return new ResponseResult(200, "查询成功",
                menuDao.getList(id), ResStatus.SUCCESS);
    }

    @Override
    public ResponseResult all() {
        return new ResponseResult(200, "查询成功",
                menuDao.all(), ResStatus.SUCCESS);
    }
}
