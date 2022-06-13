package com.woniuxy.user.controller;

import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.entity.ScfpUser;
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
    ResponseResult newsub(@RequestBody ScfpUser user) {
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
    ResponseResult modsub(@RequestBody ScfpUser user) {
        return accountService.modsub(user);
    }

    /**
     * 、
     * 查询当前管理员的子账户
     *
     * @param
     * @return
     */
    @PutMapping("/grysub")
    ResponseResult qrysub(@RequestBody ScfpUser user) {
        return accountService.qrysub(user);
    }
//    ResponseResult newadmin(@RequestBody )
}
