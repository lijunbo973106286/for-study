package com.woniuxy.commons.entity.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class LoanDTO {
    @ApiModelProperty("放款记录id")
    private int id;
    @ApiModelProperty("链单id")
    private int chain_id;
    @ApiModelProperty("企业id")
    private int enterprise_id;
    @ApiModelProperty("放款金额")
    private BigDecimal money;
    @ApiModelProperty("放款时间")
    private String loan_time;
    @ApiModelProperty("还款时间")
    private String repay_time;
    @ApiModelProperty("本金状态")
    private String principal_status;
    @ApiModelProperty("服务费")
    private BigDecimal service;
    @ApiModelProperty("服务费状态")
    private String service_status;
    @ApiModelProperty("利息")
    private BigDecimal interest;
    @ApiModelProperty("利息状态")
    private String interest_status;
    @ApiModelProperty("当前页")
    private int currentPage;
    @ApiModelProperty("每页条数")
    private int pageSize;
    @ApiModelProperty("截止兑付时间")
    private String deadline;
    //时间段查询
    /** 时间段上限 */
    private String startTime;
    /** 时间段下限 */
    private String endTime;
}
