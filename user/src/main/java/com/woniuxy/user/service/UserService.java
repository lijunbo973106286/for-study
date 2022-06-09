package com.woniuxy.user.service;

import com.woniuxy.user.entity.ResponseResult;
import com.woniuxy.user.entity.ScfpEnterprise;
import com.woniuxy.user.entity.ScfpEnterpriseAccount;
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
    ResponseResult registerone(ScfpUser user);

    ResponseResult registertwo(ScfpEnterprise enterprise);

    ResponseResult registerthree(ScfpEnterpriseAccount enterpriseAccount);
}
