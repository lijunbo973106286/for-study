package com.woniuxy.user;

import com.woniuxy.user.dao.MenuDao;
import com.woniuxy.user.dao.SubRolesDao;
import com.woniuxy.user.entity.ScfpMenu;
import com.woniuxy.user.entity.ScfpRole;
import com.woniuxy.user.entity.ScfpRoleMenu;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserApplicationTests {
    @Resource
    SubRolesDao rolesDao;
    @Test
    void contextLoads() {
        for (ScfpRole subRole : rolesDao.subRoles(78)) {
            System.out.println(subRole);
        }

    }
}
