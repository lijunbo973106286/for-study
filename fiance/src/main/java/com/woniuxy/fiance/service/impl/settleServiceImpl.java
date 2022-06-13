package com.woniuxy.fiance.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniuxy.commons.entity.DTO.EnterpriseAccountDTO;
import com.woniuxy.commons.entity.DTO.LoanDTO;
import com.woniuxy.commons.entity.DTO.ServiceChargeDTO;
import com.woniuxy.commons.entity.ScfpLoan;
import com.woniuxy.commons.util.ConvertTime;
import com.woniuxy.commons.util.ResponseResult;
import com.woniuxy.fiance.mapper.settleMapper;
import com.woniuxy.fiance.service.settleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    @Override
    public ResponseResult<LoanDTO> password(int id) {
       String pass= settleMapper.password(id);

        return responseResult.success(pass);
    }

    @Override
    public ResponseResult<LoanDTO> upstatus(int id) {
       int i= settleMapper.upstatus(id);
        if (i!=0){
            return responseResult.success(i);
        }
        return responseResult.fail();
    }

    @Override
    public void addservice() {
        //获取当前时间
        Date date = new Date();
        //当前时间毫秒值
        long time = date.getTime();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String format = dateFormat.format(date);
        //计划支付时间
        time = time + (1000 * 60 * 30);
        date.setTime(time);
        String format1 = dateFormat.format(date);
        int addservice = settleMapper.addservice(format, format1);
//        int i= settleMapper.addservice();
    }
}
