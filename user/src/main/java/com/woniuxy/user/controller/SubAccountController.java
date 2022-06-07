package com.woniuxy.user.controller;

import com.woniuxy.commons.util.ResponseResult;
import com.woniuxy.user.entity.ScfpUser;
import com.woniuxy.user.service.SubAccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.controller
 * @className: SubAccountsController
 * @author: SuYHo
 * @description:
 *对子账户的操作
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
}
