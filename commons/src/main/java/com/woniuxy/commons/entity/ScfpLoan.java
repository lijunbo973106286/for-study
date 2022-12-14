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
    @ApiModelProperty("链单编号")
    private String chain_no;
    @ApiModelProperty("链单对象")
    private ScfpChain scfpChain;
    @ApiModelProperty("企业id")
    private int enterprise_id;
    @ApiModelProperty("企业对象")
    private ScfpEnterprise scfpEnterprise;
    @ApiModelProperty("放款金额")
    private BigDecimal money;
    @ApiModelProperty("放款时间")
    private String loan_time;
    @ApiModelProperty("查询截止时间")
    private String loan_query_time;
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
    @ApiModelProperty("记录数")
    private int count;
    @ApiModelProperty("总金额")
    private BigDecimal totalMoney;
}
