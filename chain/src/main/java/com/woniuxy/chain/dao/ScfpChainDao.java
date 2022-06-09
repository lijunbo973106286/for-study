package com.woniuxy.chain.dao;

import com.woniuxy.commons.entity.ScfpChain;
import com.woniuxy.commons.entity.ScfpEnterprise;
import com.woniuxy.commons.entity.ScfpFund;

import java.util.List;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-07 10:34
 * \* @Description：
 */
public interface ScfpChainDao {

    int update(ScfpChain scfpChain);

    int insert(ScfpChain scfpChain);

    int delete(int id);

    List<ScfpChain> search(ScfpChain scfpChain);

    List<ScfpChain> findAll(ScfpChain scfpChain);

    List<ScfpFund> findScfpFund(ScfpChain scfpChain);

    List<ScfpEnterprise> findScfpEnterprises(ScfpChain scfpChain);

    List<ScfpChain> findAllExcel(int eid);

    int findAllCount(int eid);

    int findCount(ScfpChain scfpChain);

    ScfpEnterprise checkPayPass(ScfpEnterprise scfpEnterprise);

    ScfpEnterprise getEnterprise(String bankName);
}
