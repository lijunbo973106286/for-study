package com.woniuxy.user;

import com.woniuxy.user.dao.MenuDao;
import com.woniuxy.user.entity.ScfpMenu;
import com.woniuxy.user.entity.ScfpRoleMenu;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserApplicationTests {

    @Resource
    MenuDao menuDao;

    @Test
    void contextLoadsRole() {
        List<ScfpMenu> byRoleId = menuDao.list(2);
        byRoleId.forEach(System.out::println);
    }
    @Test
    void contextLoads() {
    }

}
