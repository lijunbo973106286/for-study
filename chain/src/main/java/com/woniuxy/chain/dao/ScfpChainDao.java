package com.woniuxy.chain.dao;

import com.woniuxy.commons.entity.*;

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

    ScfpFundAccount checkPayPass(ScfpFundAccount scfpFundAccount);

    ScfpEnterprise getEnterprise(String bankName);

    List<ScfpChain> findAllLoan(ScfpChain scfpChain);

    int findLoanCount(ScfpChain scfpChain);

    List<ScfpEnterprise> findAllEnterprise();

    List<ScfpFund> findAllFund();

    ScfpChainStatus findStatus(int cs_id);

    ScfpChain findById(ScfpChain scfpChain);

    List<ScfpEnterprise> findEnterprise();

    ScfpEnterprise findEnterpriseByName(String ename);

    ScfpEnterprise findCore(int chain_id);

    int updateEnterprise(ScfpEnterprise scfpEnterprise);
}
