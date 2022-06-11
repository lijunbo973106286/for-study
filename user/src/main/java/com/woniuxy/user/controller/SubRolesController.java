package com.woniuxy.user.controller;

import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.entity.ScfpRole;
import com.woniuxy.user.service.SubRolesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.controller
 * @className: SubRolesController
 * @author: SuYHo
 * @description: 子账号角色操作
 * @date: 2022/6/7 20:39
 * @version: 1.0
 */
@RequestMapping("/subRoles")
@RestController
public class SubRolesController {
    @Resource
    SubRolesService subRolesService;

    /**
     * 角色创建
     */
    @PostMapping("/create")
    ResponseResult create(@RequestBody ScfpRole role) {
        return subRolesService.create(role);
    }

    /**
     * 角色删除
     */
    @DeleteMapping("/delete/{id}")
    ResponseResult delete(@PathVariable("id") int id) {
        return subRolesService.delete(id);
    }

    /**
     * 角色修改
     */
    @PutMapping("/update")
    ResponseResult update(@RequestBody ScfpRole role) {
        return subRolesService.update(role);
    }

    /**
     * 查询全部子角色
     */
    @GetMapping("/subRoles/{id}")
    ResponseResult subRoles(@PathVariable("id") int id) {
        return subRolesService.subRoles(id);
    }

    /**
     * 根据角色id修改角色状态
     */
    @PutMapping("statusChange/{id}/{status}")
    ResponseResult statusChange(@PathVariable("id") int id, @PathVariable("status") String status) {
        return subRolesService.statusChange(id, status);
    }
}
