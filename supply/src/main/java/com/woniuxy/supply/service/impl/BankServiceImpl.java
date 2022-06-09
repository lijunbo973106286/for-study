package com.woniuxy.supply.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniuxy.commons.entity.DTO.NetworkDTO;
import com.woniuxy.commons.entity.ResStatus;
import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpEnterprise;
import com.woniuxy.supply.dao.BankDao;
import com.woniuxy.supply.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.supply.service.impl
 * @Author qfx
 * @CreateTime 2022-06-08  14:16
 * @Description TODO
 * @Version 1.0
 */
@Slf4j
@Service
public class BankServiceImpl implements BankService {
    @Resource
    BankDao bankDao;

    @Override
    public ResponseResult findAllBank() {
        List<ScfpEnterprise> all = bankDao.findAllBank();
        if (all.isEmpty()) {
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        } else {
            log.info("所有资金方：{}", all);
            return new ResponseResult(200, "查询成功", all, ResStatus.SUCCESS);
        }
    }
}
