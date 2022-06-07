package com.woniuxy.commons.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-06 17:36
 * \* @Description：
 */
@ApiModel(description = "放款记录实体类")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScfpLoan {
    @ApiModelProperty("放款记录id")
    private int id;
    @ApiModelProperty("链单id")
    private int chain_id;
    @ApiModelProperty("放款记录id")
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

}
