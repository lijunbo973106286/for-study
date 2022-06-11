package com.woniuxy.user.controller;

import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.entity.ScfpUser;
import com.woniuxy.user.entity.ScfpUserRole;
import com.woniuxy.user.service.SubAccountsService;
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
@RequestMapping("/subAccounts")
public class SubAccountsController {
    @Resource
    SubAccountsService subAccountsService;

    /**
     * 新增子账户
     */
    @PostMapping("/add")
    ResponseResult add(@RequestBody ScfpUser user) {
        return subAccountsService.add(user);
    }

    /**
     * 查询当前管理员的子账号
     */
    @GetMapping("/findByFid/{fid}")
    ResponseResult findByFid(@PathVariable("fid") int fid) {
        return subAccountsService.findByFid(fid);
    }

    /**
     * 根据子子账号id删除子账户
     */
    @DeleteMapping("/delete/{id}")
    ResponseResult delete(@PathVariable("id") int id) {
        return subAccountsService.delete(id);
    }

    /**
     * 根据id更改子账户状态
     */
    @PutMapping("/statusChange/{id}/{status}")
    ResponseResult statusChange(@PathVariable("id") int id, @PathVariable("status") String status) {
        return subAccountsService.statusChange(id, status);
    }

    /**
     * 修改子账户信息
     */
    @PutMapping("/update")
    ResponseResult update(@RequestBody ScfpUser user) {
        return subAccountsService.update(user);
    }

    /**
     * 根据条件查询子账号
     */
    @PutMapping("/search")
    ResponseResult search(@RequestBody ScfpUser user) {
        return subAccountsService.search(user);
    }

    /**
     * 表scfp_user_role
     * 修改用户角色
     */
    @PostMapping("/userRole")
    ResponseResult userRole(@RequestBody ScfpUserRole userRole) {
        return subAccountsService.userRole(userRole);
    }

    /**
     * 查询用户角色
     */
    @GetMapping("/findRoleById/{user_id}")
    ResponseResult findRoleById(@PathVariable("user_id") int user_id) {
        return subAccountsService.findRoleById(user_id);
    }

}
