package com.woniuxy.fiance.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.woniuxy.commons.entity.DTO.LoanDTO;
import com.woniuxy.commons.entity.DTO.ServiceChargeDTO;
import com.woniuxy.commons.util.ResponseResult;
import com.woniuxy.fiance.service.settleService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/settle")
public class settlecontroller {


    @Resource
    private com.woniuxy.fiance.service.settleService settleService;
    //应付手续费查询
    @PostMapping("/handlingFee/{currentPate}/{pageSize}/{id}")
    public ResponseResult findsettle(@PathVariable("currentPate")int currentPate, @PathVariable("pageSize")int pageSize, @PathVariable("id") int id){
        return settleService.findsettle(currentPate,pageSize,id);
    }
    //已支付的手续费
    @PostMapping("/Paid/{currentPate}/{pageSize}/{id}")
    public ResponseResult Paid(@PathVariable("currentPate")int currentPate, @PathVariable("pageSize")int pageSize, @PathVariable("id") int id){
        return settleService.Paid(currentPate,pageSize,id);
    }
    //未支付的手续费
    @PostMapping("/unpaid/{currentPate}/{pageSize}/{id}")
    public ResponseResult unpaid(@PathVariable("currentPate")int currentPate, @PathVariable("pageSize")int pageSize, @PathVariable("id") int id){
        return settleService.unpaid(currentPate,pageSize,id);
    }
    //条件查询
    @PostMapping("/search")
    public ResponseResult<ServiceChargeDTO> search(@RequestBody ServiceChargeDTO loanDTO){
        return settleService.search(loanDTO);
    }
    //密码
    @PostMapping("/password/{id}")
    public ResponseResult password(@PathVariable("id") int id){
        return settleService.password(id);
    }
    //修改支付状态
    @PostMapping("status/{eid}/{sid}")
    public ResponseResult<ServiceChargeDTO> status(@PathVariable("eid") int eid,@PathVariable("sid") int sid){
        return  settleService.upstatus(eid,sid);

    }
    //平台按时发平台服务费
//    @Scheduled(fixedRate=3000)
    @Scheduled(cron = "0 0/30 * * * ?")
    public void addservice(){
        settleService.addservice();
    }

}
