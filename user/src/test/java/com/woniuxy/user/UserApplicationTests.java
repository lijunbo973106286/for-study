package com.woniuxy.user;

import com.woniuxy.user.dao.AccountDao;
import com.woniuxy.user.dao.MenuDao;
import com.woniuxy.user.dao.RoleDao;
import com.woniuxy.user.entity.ScfpMenu;
import com.woniuxy.user.entity.ScfpRole;
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

    @Test
    void contextLoads() {

    }
}
