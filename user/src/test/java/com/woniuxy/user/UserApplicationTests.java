package com.woniuxy.user;

import com.woniuxy.user.dao.RoleDao;
import com.woniuxy.user.entity.ScfpRole;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserApplicationTests {
    @Resource
    RoleDao rolesDao;
    @Test
    void contextLoads() {

    }
}
