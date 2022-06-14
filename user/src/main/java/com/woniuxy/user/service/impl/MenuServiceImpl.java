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
import java.util.stream.Collectors;

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

    @Override
    public ResponseResult getRM(int id) {
        return new ResponseResult(200, "查询成功",
                new ScfpRoleMenu(
                        id, menuDao.getRM(id)),
                ResStatus.SUCCESS);
    }

    @Override
    @Transactional
    public ResponseResult setRM(ScfpRoleMenu roleMenu) {
        if (roleMenu.getMenu_id() == null) {
            return menuDao.delRM(roleMenu) > 0 ? new ResponseResult(200, "操作成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "操作失败", null, ResStatus.FAIL);
        } else {
            menuDao.delRM(roleMenu);
            List<Integer> fm = menuDao.getFM(roleMenu);
            roleMenu.getMenu_id().addAll(fm);
            List<Integer> newRoleMenu = roleMenu.getMenu_id().stream().distinct().collect(Collectors.toList());
            roleMenu.setMenu_id(newRoleMenu);
            return menuDao.setRM(roleMenu) > 0 ? new ResponseResult(200, "操作成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "操作失败", null, ResStatus.FAIL);
        }
    }
}
