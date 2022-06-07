package com.woniuxy.user.service;

import com.woniuxy.commons.util.ResponseResult;
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
public interface SubAccountService {
    ResponseResult add(ScfpUser user);
}
