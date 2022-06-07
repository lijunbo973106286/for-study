package com.woniuxy.user.service.impl;

import com.woniuxy.commons.util.ResStatus;
import com.woniuxy.commons.util.ResponseResult;
import com.woniuxy.user.dao.SubAccountDao;
import com.woniuxy.user.entity.ScfpUser;
import com.woniuxy.user.service.SubAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.service.impl
 * @className: SubAccountServiceImpl
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/7 15:04
 * @version: 1.0
 */
@Service
public class SubAccountServiceImpl implements SubAccountService {
    @Resource
    SubAccountDao subAccountDao;

    @Override
    public ResponseResult add(ScfpUser user) {
        int add = subAccountDao.add(user);
        if (add > 0) {
            return new ResponseResult(200, "添加成功", null, ResStatus.SUCCESS);
        } else {
            return new ResponseResult(500, "添加失败", null, ResStatus.FAIL);
        }
    }
}
