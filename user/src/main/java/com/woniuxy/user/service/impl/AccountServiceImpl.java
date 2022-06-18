package com.woniuxy.user.service.impl;

import com.woniuxy.user.dao.AccountDao;
import com.woniuxy.user.dao.SupplyDao;
import com.woniuxy.user.entity.*;
import com.woniuxy.user.service.AccountService;
import net.sf.jsqlparser.statement.select.Select;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class AccountServiceImpl implements AccountService {
    @Resource
    AccountDao accountDao;
    @Resource
    SupplyDao supplyDao;

    @Override
    @Transactional
    public ResponseResult newsub(UserDTO user) {
        if (accountDao.newsub(user) > 0) {
            int user_id = accountDao.getUserId(user);
            return accountDao.newsubrole(user_id, user.getRole_id()) > 0 ? new ResponseResult(200, "操作成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "操作失败", null, ResStatus.FAIL);
        }
        return new ResponseResult(500, "操作失败", null, ResStatus.FAIL);
    }

    @Override
    @Transactional
    public ResponseResult delsub(int id) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        return accountDao.delsub(id) > 0 && accountDao.delrole(userDTO) > 0 ? new ResponseResult(200, "操作成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "操作失败", null, ResStatus.FAIL);
    }

    @Override
    @Transactional
    public ResponseResult modsub(UserDTO user) {
        if (user.getRole_id() == 0) {
            return accountDao.delrole(user) > 0 && accountDao.modsub(user) > 0 ? new ResponseResult(200, "操作成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "操作失败", null, ResStatus.FAIL);
        } else {
            accountDao.delrole(user);
            return accountDao.inrole(user) > 0 && accountDao.modsub(user) > 0 ? new ResponseResult(200, "操作成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "操作失败", null, ResStatus.FAIL);
        }
    }

    @Override
    public ResponseResult qrysub(UserDTO user) {
        return new ResponseResult(200, "查询成功",
                accountDao.qrysub(user), ResStatus.SUCCESS);
    }

    @Override
    public UserDTO login(ScfpUser user) {
        return accountDao.login(user);
    }

    @Override
    public ResponseResult modstatus(ScfpUser user) {
        return accountDao.modstatus(user) > 0 ? new ResponseResult(200, "操作成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "操作失败", null, ResStatus.FAIL);

    }

    @Override
    public ResponseResult modpwd(ScfpUser user) {
        return accountDao.modpwd(user) > 0 ? new ResponseResult(200, "操作成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "操作失败", null, ResStatus.FAIL);

    }

    @Override
    public ResponseResult userInfo(int id) {
        return new ResponseResult(200, "查询成功",
                accountDao.userInfo(id), ResStatus.SUCCESS);
    }

    @Override
    public ResponseResult checkOldPwd(ScfpUser user) {
        return accountDao.checkOldPwd(user) != null ? new ResponseResult(200, "密码验证成功", null, ResStatus.SUCCESS) : new ResponseResult(500, "密码验证失败，该密码与原密码不一致", null, ResStatus.FAIL);
    }

    @Override
    public ResponseResult corpInfo(int id) {
        return new ResponseResult(200, "查询成功",
                accountDao.corpInfo(id), ResStatus.SUCCESS);
    }

    @Override
    @Transactional
    public ResponseResult register(Register register) {
        if (accountDao.corpRegist(register) > 0) {
            int corpID = accountDao.corpID(register);
            int managerRID = accountDao.managerRID(register);
            register.setCorpId(corpID);
            if (accountDao.managerRegist(register) > 0) {
                int managerID = accountDao.managerID(register);
                if (accountDao.newsubrole(managerID, managerRID) > 0) {
                    if(3==managerRID){
                        supplyDao.add(corpID);
                    }
                    return new ResponseResult(200, "操作成功", null, ResStatus.SUCCESS);
                } else {
                   return new ResponseResult(500, "操作失败", null, ResStatus.FAIL);
                }
            }
            return new ResponseResult(500, "操作失败", null, ResStatus.FAIL);
        }
        return new ResponseResult(500, "操作失败", null, ResStatus.FAIL);
    }
}
