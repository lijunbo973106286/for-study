package com.woniuxy.user.controller;

import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.entity.ScfpUser;
import com.woniuxy.user.entity.UserDTO;
import com.woniuxy.user.service.AccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.controller
 * @className: SubAccountsController
 * @author: SuYHo
 * @description: 对子账户的操作
 * @date: 2022/6/7 14:54
 * @version: 1.0
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Resource
    AccountService accountService;

    /**
     * 新增子账号
     *
     * @param
     * @return
     */
    @PostMapping("/newsub")
    ResponseResult newsub(@RequestBody UserDTO user) {
        return accountService.newsub(user);
    }

    /**
     * 删除子账号
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delsub/{id}")
    ResponseResult delsub(@PathVariable("id") int id) {
        return accountService.delsub(id);
    }

    /**
     * 、
     * 修改子账号
     *
     * @param user
     * @return
     */
    @PutMapping("/modsub")
    ResponseResult modsub(@RequestBody UserDTO user) {
        return accountService.modsub(user);
    }

    /**
     * 、
     * 查询当前管理员的子账户
     *
     * @param
     * @return
     */
    @PutMapping("qrysub")
    ResponseResult qrysub(@RequestBody UserDTO user) {
        return accountService.qrysub(user);
    }

    /**
     * 修改账号状态
     *
     * @param user
     * @return
     */
    @PutMapping("/modstatus")
    ResponseResult modstatus(@RequestBody ScfpUser user) {
        return accountService.modstatus(user);
    }

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    @PutMapping("/modpwd")
    ResponseResult modpwd(@RequestBody ScfpUser user) {
        return accountService.modpwd(user);
    }

    /**
     * 个人信息
     *
     * @param id
     * @return
     */
    @GetMapping("/userInfo/{id}")
    ResponseResult userInfo(@PathVariable("id") int id) {

        return accountService.userInfo(id);
    }
}
