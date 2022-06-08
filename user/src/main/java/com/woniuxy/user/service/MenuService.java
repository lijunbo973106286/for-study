package com.woniuxy.user.service;

import com.woniuxy.commons.entity.ResponseResult;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.commons.service
 * @Author qfx
 * @CreateTime 2022-06-08  09:41
 * @Description TODO
 * @Version 1.0
 */
public interface MenuService {
    public ResponseResult findAll();

    public ResponseResult findByRoleId(int roleId);
}
