package com.woniuxy.user.controller;

import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.user.service.MenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "菜单管理接口")
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    MenuService menuService;

    @GetMapping("/findAll")
    public ResponseResult findAll(){
     return   menuService.findAll();
    }
    @GetMapping("/findByRoleId/{roleId}")
    public ResponseResult findByRoleId(@PathVariable int roleId){
        return   menuService.findByRoleId(roleId);
    }
}
