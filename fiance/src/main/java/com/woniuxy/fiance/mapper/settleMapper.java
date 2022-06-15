package com.woniuxy.fiance.mapper;

import com.woniuxy.commons.entity.DTO.ServiceChargeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface settleMapper {
    public List<ServiceChargeDTO> findsettle(int id) ;

    List<ServiceChargeDTO> Paid(int id);

    List<ServiceChargeDTO> unpaid(int id);

    List<ServiceChargeDTO> search(ServiceChargeDTO loanDTO);

    String password(int id);

    int upstatus(@Param("eid") int eid,@Param("sid") int sid);

    int addservice(ServiceChargeDTO serviceChargeDTO);
    //查询所有企业id
    int[] findEId();

    void addenterpriseServiceFee(List<ServiceChargeDTO> list);
    //查询 findSid();

}
