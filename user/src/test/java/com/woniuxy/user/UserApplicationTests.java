package com.woniuxy.user;

import com.woniuxy.user.dao.AccountDao;
import com.woniuxy.user.dao.MenuDao;
import com.woniuxy.user.dao.RoleDao;
import com.woniuxy.user.entity.*;
import com.woniuxy.user.service.MenuService;
import org.assertj.core.internal.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserApplicationTests {
    @Resource
    RoleDao rolesDao;
    @Resource
    MenuDao menuDao;
    @Resource
    AccountDao accountDao;
    @Resource
    MenuService menuService;

    @Test
    void contextLoads() {
        ScfpRoleMenu scfpRoleMenu = new ScfpRoleMenu();
        List<Integer> objects = new ArrayList<>();
        objects.add(15);
        objects.add(16);
        objects.add(3);
        objects.add(1);
        scfpRoleMenu.setMenu_id(objects);
        List<Integer> fm = menuDao.getFM(scfpRoleMenu);
        fm.addAll(objects);
        System.out.println(fm);
        scfpRoleMenu.setMenu_id(fm);
        System.out.println(scfpRoleMenu);
    }
}
