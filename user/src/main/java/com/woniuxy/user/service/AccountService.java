package com.woniuxy.user.service;

import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.entity.ScfpUser;
import com.woniuxy.user.entity.UserDTO;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    ResponseResult newsub(UserDTO user);

    ResponseResult delsub(int id);

    ResponseResult modsub(UserDTO user);

    ResponseResult qrysub(UserDTO user);

    UserDTO login(ScfpUser user);
}
