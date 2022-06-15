package com.woniuxy.commons.entity.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceChargeDTO {
    @ApiModelProperty("服务表id")
    private int id;
    @ApiModelProperty("创建服务费时间")
    private String month;
    @ApiModelProperty("服务费用")
    private int money;
    @ApiModelProperty("截至支付时间")
    private String plan_time;
    @ApiModelProperty("企业id")
    private int eid;
    @ApiModelProperty("服务费id")
    private int sid;
    @ApiModelProperty("支付服务费时间")
    private String pay_time;
    @ApiModelProperty("服务费状态")
    private String status;
    @ApiModelProperty("当前页")
    private int currentPage;
    @ApiModelProperty("每页条数")
    private int pageSize;
    /** 时间段上限 */
    private String startTime;
    /** 时间段下限 */
    private String endTime;
}
