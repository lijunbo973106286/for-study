package com.woniuxy.user;

import com.woniuxy.user.dao.MenuDao;
import com.woniuxy.user.entity.ScfpMenu;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class UserApplicationTests {

    @Resource
    MenuDao menuDao;

    @Test
    void contextLoads() {
        List<ScfpMenu> all = menuDao.findAll();
        all.forEach(System.out::println);
    }
    @Test
    void contextLoadsRole() {
        List<ScfpMenu> byRoleId = menuDao.findByRoleId(1);
        byRoleId.forEach(System.out::println);
    }

}
