package com.woniuxy.user.controller;

import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 获取全部菜单
     *
     * @return
     */
    @GetMapping("/all")
    ResponseResult all() {
        return menuService.all();
    }

    /**
     * 查询该管理员拥有的菜单
     *
     * @param id
     * @return
     */
    @GetMapping("/getList/{id}")
    ResponseResult getList(@PathVariable("id") int id) {
        return menuService.getList(id);
    }
}
