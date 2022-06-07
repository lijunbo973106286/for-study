package com.woniuxy.commons.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-06 17:07
 * \* @Description：
 */
@ApiModel(description = "链单实体类")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScfpChain {
    @ApiModelProperty("链单id")
    private int id;
    @ApiModelProperty("链单编号")
    private String chain_no;
    @ApiModelProperty("流转网络id")
    private int network_id;
    @ApiModelProperty("银行")
    private String bank;
    @ApiModelProperty("资金产品id")
    private int fund_id;
    @ApiModelProperty("融资总额度")
    private BigDecimal money;
    @ApiModelProperty("剩余额度")
    private BigDecimal surplus;
    @ApiModelProperty("附件id")
    private int file_id;
    @ApiModelProperty("截止兑付时间")
    private String deadline;
    @ApiModelProperty("链单当前状态")
    private String status;
    @ApiModelProperty("当前页")
    private int currentPage;
    @ApiModelProperty("每页条数")
    private int pageSize;
}
