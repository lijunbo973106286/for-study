package com.woniuxy.fiance.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniuxy.commons.entity.DTO.EnterpriseAccountDTO;
import com.woniuxy.commons.entity.DTO.LoanDTO;
import com.woniuxy.commons.entity.ScfpLoan;
import com.woniuxy.commons.util.ResponseResult;
import com.woniuxy.fiance.mapper.settleMapper;
import com.woniuxy.fiance.service.settleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class settleServiceImpl implements settleService {
    ResponseResult responseResult = ResponseResult.getResponseResult();
    @Resource
    private settleMapper settleMapper;
    /*手续费查询*/
    @Override
    public ResponseResult findsettle(int currentPate, int pageSize, int id) {
        PageHelper.startPage(currentPate, pageSize);
        List<LoanDTO> loans= settleMapper.findsettle(id);
        if (loans!=null){
            PageInfo<LoanDTO> list = PageInfo.of(loans);
           return responseResult.success(list);
       }
        return responseResult.fail();
    }

    @Override
    public ResponseResult Paid(int currentPate, int pageSize, int id) {
        PageHelper.startPage(currentPate, pageSize);
        List<LoanDTO> loans= settleMapper.Paid(id);
        if (loans!=null){
            PageInfo<LoanDTO> list = PageInfo.of(loans);
            return responseResult.success(list);
        }
        return responseResult.fail();
    }

    @Override
    public ResponseResult unpaid(int currentPate, int pageSize, int id) {
        PageHelper.startPage(currentPate, pageSize);
        List<LoanDTO> loans= settleMapper.unpaid(id);
        if (loans!=null){
            PageInfo<LoanDTO> list = PageInfo.of(loans);
            return responseResult.success(list);
        }
        return responseResult.fail();
    }
    //条件查询
    @Override
    public ResponseResult<LoanDTO> search(LoanDTO loanDTO) {
        PageHelper.startPage(loanDTO.getCurrentPage(), loanDTO.getPageSize());
        List<LoanDTO> loans=  settleMapper.search(loanDTO);
        System.out.println(loans);
        if (loans!=null){
            PageInfo<LoanDTO> list = PageInfo.of(loans);
            return responseResult.success(list);
        }
        return responseResult.fail();
    }
}
