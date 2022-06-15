package com.woniuxy.fiance.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniuxy.commons.entity.DTO.ServiceChargeDTO;
import com.woniuxy.commons.util.ResponseResult;
import com.woniuxy.fiance.mapper.settleMapper;
import com.woniuxy.fiance.service.settleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        List<ServiceChargeDTO> loans= settleMapper.findsettle(id);
        if (loans!=null){
            PageInfo<ServiceChargeDTO> list = PageInfo.of(loans);
           return responseResult.success(list);
       }
        return responseResult.fail();
    }

    @Override
    public ResponseResult Paid(int currentPate, int pageSize, int id) {
        PageHelper.startPage(currentPate, pageSize);
        List<ServiceChargeDTO> loans= settleMapper.Paid(id);
        if (loans!=null){
            PageInfo<ServiceChargeDTO> list = PageInfo.of(loans);
            return responseResult.success(list);
        }
        return responseResult.fail();
    }

    @Override
    public ResponseResult unpaid(int currentPate, int pageSize, int id) {
        PageHelper.startPage(currentPate, pageSize);
        List<ServiceChargeDTO> loans= settleMapper.unpaid(id);
        if (loans!=null){
            PageInfo<ServiceChargeDTO> list = PageInfo.of(loans);
            return responseResult.success(list);
        }
        return responseResult.fail();
    }
    //条件查询handlingFee
    @Override
    public ResponseResult<ServiceChargeDTO> search(ServiceChargeDTO loanDTO) {
        PageHelper.startPage(loanDTO.getCurrentPage(), loanDTO.getPageSize());
        List<ServiceChargeDTO> loans=  settleMapper.search(loanDTO);
        System.out.println(loans);
        if (loans!=null){
            PageInfo<ServiceChargeDTO> list = PageInfo.of(loans);
            return responseResult.success(list);
        }
        return responseResult.fail();
    }

    @Override
    public ResponseResult<ServiceChargeDTO> password(int id) {
       String pass= settleMapper.password(id);
        return responseResult.success(pass);
    }

    @Override
    public ResponseResult<ServiceChargeDTO> upstatus(int eid, int sid) {
       int i= settleMapper.upstatus(eid,sid);
        if (i!=0){
            return responseResult.success(i);
        }
        return responseResult.fail();
    }

    @Transactional
    @Override
    //定时发送月租
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
        ServiceChargeDTO serviceChargeDTO=new ServiceChargeDTO();
        serviceChargeDTO.setMonth(format);
        serviceChargeDTO.setPlan_time(format1);
        int addservice = settleMapper.addservice(serviceChargeDTO);
        //获取sid
        int sid = serviceChargeDTO.getId();
        List<ServiceChargeDTO>list=new ArrayList<>();
        ServiceChargeDTO serviceChargeDTO1;
        //获取企业eid
        int eid[]= settleMapper.findEId();
        for (int i : eid) {
            serviceChargeDTO1=new ServiceChargeDTO();
            serviceChargeDTO1.setEid(i);
            serviceChargeDTO1.setSid(sid);
            serviceChargeDTO1.setStatus("未支付");
            list.add(serviceChargeDTO1);
        }
        //将月租发给所有企业
        settleMapper.addenterpriseServiceFee(list);
    }
}
