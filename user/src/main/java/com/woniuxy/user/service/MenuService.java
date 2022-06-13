package com.woniuxy.user.service;

import com.woniuxy.user.entity.ResponseResult;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.service
 * @className: MenuService
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/8 11:55
 * @version: 1.0
 */
public interface MenuService {
    ResponseResult getList(int id);

    ResponseResult all();
}
