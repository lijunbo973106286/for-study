package com.woniuxy.user.controller;


import com.woniuxy.user.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.controller
 * @className: UserController
 * @author: SuYHo
 * @description: 用户操作
 * @date: 2022/6/8 16:42
 * @version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    /**
     * 查询角色菜单
     */

    
}
