package com.woniuxy.user.controller;

import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.entity.RoleDTO;
import com.woniuxy.user.entity.ScfpRole;
import com.woniuxy.user.service.RoleService;
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
@RequestMapping("/role")
@RestController
public class RoleController {
    @Resource
    RoleService roleService;

    /**
     * 创建角色
     *
     * @param role
     * @return
     */
    @PostMapping("/newrole")
    ResponseResult newrole(@RequestBody ScfpRole role) {
        return roleService.newrole(role);
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @DeleteMapping("delrole/{id}")
    ResponseResult delrole(@PathVariable("id") int id) {
        return roleService.delrole(id);
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @PutMapping("/modrole")
    ResponseResult modrole(@RequestBody ScfpRole role) {
        return roleService.modrole(role);
    }

    /**
     * 查询该管理员的子角色
     *
     * @param id
     * @return
     */
    @PutMapping("/qryrole")
    ResponseResult qryrole(@RequestBody RoleDTO role) {
        return roleService.qryrole(role);
    }

    /**
     * 获取企业类型
     *
     * @return
     */
    @GetMapping("/corp")
    ResponseResult corpType() {
        return roleService.corpType();
    }
}
