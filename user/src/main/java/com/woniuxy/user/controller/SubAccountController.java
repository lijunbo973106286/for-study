package com.woniuxy.user.controller;

import com.woniuxy.commons.util.ResponseResult;
import com.woniuxy.user.entity.ScfpUser;
import com.woniuxy.user.service.SubAccountService;
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
@RequestMapping("/sub_ac")
public class SubAccountController {
    @Resource
    SubAccountService subAccountService;

    /**
     * 新增子账户
     */
    @PostMapping("/add")
    ResponseResult add(@RequestBody ScfpUser user) {
        return subAccountService.add(user);
    }

    /**
     * 查询当前管理员的子账号
     */
    @GetMapping("/findByFid/{fid}")
    ResponseResult findByFid(@PathVariable("fid") int fid) {
        return subAccountService.findByFid(fid);
    }

    /**
     * 根据子子账号id删除子账户
     */
    @DeleteMapping("/delete/{id}")
    ResponseResult delete(@PathVariable("id") int id) {
        return subAccountService.delete(id);
    }

    /**
     * 根据id更改子账户状态
     */
    @PutMapping("/statusChange/{id}/{status}")
    ResponseResult statusChange(@PathVariable("id") int id, @PathVariable("status") String status) {
        return subAccountService.statusChange(id, status);
    }

    /**
     * 修改子账户信息
     */
    @PutMapping("/update")
    ResponseResult update(@RequestBody ScfpUser user) {
        return subAccountService.update(user);
    }
    /**
     * 根据条件查询子账号
     */
    @PutMapping("/search")
    ResponseResult search(@RequestBody ScfpUser user){
        return subAccountService.search(user);
    }
}
