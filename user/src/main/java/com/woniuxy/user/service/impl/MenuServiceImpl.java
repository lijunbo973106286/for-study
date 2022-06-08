package com.woniuxy.user.service.impl;

import com.woniuxy.user.dao.MenuDao;
import com.woniuxy.commons.entity.ResStatus;
import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpMenu;
import com.woniuxy.user.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.commons.service.impl
 * @Author qfx
 * @CreateTime 2022-06-08  09:42
 * @Description TODO
 * @Version 1.0
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    MenuDao menuDao;

    @Override
    public ResponseResult findAll() {
        List<ScfpMenu> all = menuDao.findAll();
        if (all.isEmpty()) {
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        } else {
            return new ResponseResult(200, "查询成功", all, ResStatus.SUCCESS);
        }
    }

    @Override
    public ResponseResult findByRoleId(int roleId) {
        return null;
    }
}
