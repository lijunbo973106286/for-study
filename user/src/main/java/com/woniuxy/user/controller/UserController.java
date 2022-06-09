package com.woniuxy.user.controller;

import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.entity.ScfpUser;
import com.woniuxy.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.controller
 * @className: UserController
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/9 17:47
 * @version: 1.0
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Resource
    UserService userService;
    /**
     * 创建角色账号
     */
    @PostMapping("/register")
    ResponseResult register(@RequestBody ScfpUser user){
        return userService.register(user);
    }
}
