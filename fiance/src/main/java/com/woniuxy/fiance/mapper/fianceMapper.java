package com.woniuxy.fiance.mapper;

import com.woniuxy.commons.entity.DTO.EnterpriseAccountDTO;
import com.woniuxy.commons.entity.ScfpEnterpriseAccount;
import com.woniuxy.commons.util.ResponseResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface fianceMapper {
   public List<EnterpriseAccountDTO> findbankaccount(int id);

   int delaccount(int seaid);

   int modifyaccount(ScfpEnterpriseAccount scfpEnterpriseAccount);

   int addaccount(ScfpEnterpriseAccount scfpEnterpriseAccount);
}
