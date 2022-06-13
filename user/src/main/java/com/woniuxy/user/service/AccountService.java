package com.woniuxy.user.service;

import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.entity.ScfpUser;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.service
 * @className: SubAccountService
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/7 15:03
 * @version: 1.0
 */
public interface AccountService {

    ResponseResult newsub(ScfpUser user);

    ResponseResult delsub(int id);

    ResponseResult modsub(ScfpUser user);

    ResponseResult qrysub(ScfpUser user);

    ScfpUser login(ScfpUser user);
}
