package com.woniuxy.fiance.mapper;

import com.woniuxy.commons.entity.DTO.LoanDTO;
import com.woniuxy.commons.entity.ScfpLoan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface settleMapper {
    public List<LoanDTO> findsettle(int id) ;

    List<LoanDTO> Paid(int id);

    List<LoanDTO> unpaid(int id);

    List<LoanDTO> search(LoanDTO loanDTO);

    String password(int id);

    int upstatus(int id);

    int addservice();
}
