package com.woniuxy.fiance.mapper;

import com.woniuxy.commons.entity.DTO.LoanDTO;
import com.woniuxy.commons.entity.DTO.ServiceChargeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface settleMapper {
    public List<LoanDTO> findsettle(int id) ;

    List<LoanDTO> Paid(int id);

    List<LoanDTO> unpaid(int id);

    List<LoanDTO> search(LoanDTO loanDTO);

    String password(int id);

    int upstatus(int id);

    int addservice(ServiceChargeDTO serviceChargeDTO);
    //查询所有企业id
    int[] findEId();

    void addenterpriseServiceFee(List<ServiceChargeDTO> list);
    //查询 findSid();

}
