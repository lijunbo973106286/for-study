package com.woniuxy.fiance.service;

import com.woniuxy.commons.entity.DTO.EnterpriseAccountDTO;
import com.woniuxy.commons.entity.ScfpEnterpriseAccount;
import com.woniuxy.commons.util.ResponseResult;

public interface fianceService {
    ResponseResult<EnterpriseAccountDTO> findbankaccount(int currentPate,int pageSize,int id);

    ResponseResult delaccount(int id);

    ResponseResult modifyaccount(ScfpEnterpriseAccount scfpEnterpriseAccount);

    ResponseResult add(ScfpEnterpriseAccount scfpEnterpriseAccount);
}
