package com.woniuxy.fiance.service;

import com.woniuxy.commons.entity.DTO.ServiceChargeDTO;
import com.woniuxy.commons.util.ResponseResult;

public interface settleService {
    ResponseResult findsettle(int currentPate, int pageSize, int id);

    ResponseResult Paid(int currentPate, int pageSize, int id);

    ResponseResult unpaid(int currentPate, int pageSize, int id);

    ResponseResult<ServiceChargeDTO> search(ServiceChargeDTO loanDTO);

    ResponseResult<ServiceChargeDTO> password(int id);

    ResponseResult<ServiceChargeDTO> upstatus(int eid,int sid);

    void addservice();

    ResponseResult<ServiceChargeDTO> unpaidnum(int eid);
}
