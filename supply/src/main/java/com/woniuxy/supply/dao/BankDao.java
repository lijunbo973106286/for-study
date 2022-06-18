package com.woniuxy.supply.dao;

import com.woniuxy.commons.entity.DTO.NetworkDTO;
import com.woniuxy.commons.entity.ScfpAmount;
import com.woniuxy.commons.entity.ScfpEnterprise;

import java.util.List;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.supply.dao
 * @Author qfx
 * @CreateTime 2022-06-08  14:18
 * @Description TODO
 * @Version 1.0
 */
public interface BankDao {
    List<ScfpEnterprise> findAllBank();

    List<ScfpAmount> allAmount(ScfpAmount scfpAmount);

    List<ScfpAmount> findBy(ScfpAmount scfpAmount);

    int credit(int eid);

    ScfpAmount findCredit(int eid);
}
