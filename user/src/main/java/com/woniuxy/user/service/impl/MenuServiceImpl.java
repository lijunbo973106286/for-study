package com.woniuxy.user.service.impl;

import com.woniuxy.user.dao.MenuDao;
import com.woniuxy.user.entity.ResStatus;
import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.entity.ScfpRoleMenu;
import com.woniuxy.user.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    public ResponseResult findAll() {
        return new ResponseResult(200, "查询成功",
                menuDao.findAll(), ResStatus.SUCCESS);
    }

    @Override
    @Transactional
    public ResponseResult roleMenu(ScfpRoleMenu roleMenu) {
        if (roleMenu.getMenu_id().size() > 0) {
            menuDao.delete(roleMenu);
            return menuDao.roleMenu(roleMenu) > 0 ? new ResponseResult(200, "权限修改成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "权限修改失败", null, ResStatus.FAIL);
        } else {
            menuDao.delete(roleMenu);
            return new ResponseResult(200, "权限已清空", null, ResStatus.SUCCESS);
        }
    }

    @Override
    public ResponseResult findMenuById(int role_id) {
        return new ResponseResult(200, "查询成功",
                new ScfpRoleMenu(
                        role_id, menuDao.findMenuById(role_id)
                ), ResStatus.SUCCESS);
    }
}
