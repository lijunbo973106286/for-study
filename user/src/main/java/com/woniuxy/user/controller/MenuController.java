package com.woniuxy.user.controller;

import com.woniuxy.user.entity.ResStatus;
import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.entity.ScfpRoleMenu;
import com.woniuxy.user.service.MenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.controller
 * @className: MenuController
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/8 11:53
 * @version: 1.0
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    MenuService menuService;

    /**
     * 查询全部菜单
     */
    @GetMapping("/findAll")
    ResponseResult findAll() {
        return menuService.findAll();
    }

    /**
     * 表scfp_role_meanu
     * 角色菜单修改
     */
    @PostMapping("/roleMenu")
    ResponseResult roleMenu(@RequestBody ScfpRoleMenu roleMenu) {
        return menuService.roleMenu(roleMenu);
    }

    /**
     * 查询角色菜单
     */
    @GetMapping("/findMenuById/{role_id}")
    ResponseResult findMenuById(@PathVariable("role_id") int role_id) {
        return menuService.findMenuById(role_id);
    }
}
