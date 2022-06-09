package com.woniuxy.user.service;

import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.entity.ScfpUser;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.service
 * @className: UserService
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/9 17:51
 * @version: 1.0
 */
public interface UserService {
    ResponseResult register(ScfpUser user);
}
