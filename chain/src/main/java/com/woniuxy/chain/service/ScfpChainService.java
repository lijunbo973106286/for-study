package com.woniuxy.chain.service;

import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpChain;
import com.woniuxy.commons.entity.ScfpEnterprise;
import com.woniuxy.commons.entity.ScfpFund;

import java.util.List;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-07 10:30
 * \* @Description：
 */
public interface ScfpChainService {
    ResponseResult<Object> update(ScfpChain scfpChain);

    ResponseResult<Object> insert(ScfpChain scfpChain);

    ResponseResult<Object> delete(int id);

    ResponseResult<ScfpChain> search(ScfpChain scfpChain);

    ResponseResult<Object> findAll(ScfpChain scfpChain);

    List<ScfpChain> findAllExcel(int eid);

    ResponseResult<Object> findCount(ScfpChain scfpChain);

    ResponseResult<Object> findAllCount(int eid);

    ResponseResult<Object> updateLoan(int chain_id);

    ResponseResult<Object> updateBatLoan(List<Integer> ids);

    ResponseResult<Object> checkPayPass(ScfpEnterprise scfpEnterprise);

    ResponseResult<ScfpEnterprise> getEnterprise(String bankName);

    ResponseResult<ScfpEnterprise> findAllLoan(ScfpChain scfpChain);

    ResponseResult<Object> findLoanCount(ScfpChain scfpChain);

    ResponseResult<ScfpEnterprise> findAllEnterprise();

    ResponseResult<ScfpFund> findAllFund();

    ResponseResult<Object> findStatus(int cs_id);

    ResponseResult<ScfpChain> findById(ScfpChain scfpChain);

    ResponseResult<ScfpEnterprise> findEnterprise();

    ResponseResult<Object> findEnterpriseByName(String ename);

    ResponseResult<ScfpEnterprise> findCore(int chain_id);

    ResponseResult<Object> updateEnterprise(ScfpEnterprise scfpEnterprise);
}
