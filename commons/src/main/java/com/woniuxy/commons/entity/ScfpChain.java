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
    @ApiModelProperty("核心企业id")
    private int enterprise_id;
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
    @ApiModelProperty("兑付日止")
    private String deadlineMax;
    @ApiModelProperty("链单当前状态")
    private int status;
    @ApiModelProperty("审核意见")
    private String reason;
    @ApiModelProperty("创建人")
    private int creator;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("更新人")
    private int updator;
    @ApiModelProperty("更新时间")
    private String update_time;
    @ApiModelProperty("当前页")
    private int currentPage;
    @ApiModelProperty("每页条数")
    private int pageSize;
    @ApiModelProperty("开单人信息")
    private ScfpUser scfpUser;
    @ApiModelProperty("审核人信息")
    private ScfpUser scfpUserAppr;
    @ApiModelProperty("资金产品信息")
    private ScfpFund scfpFund;
    @ApiModelProperty("银行信息")
    private ScfpEnterprise scfpEnterprise;
    @ApiModelProperty("排序字段")
    private String sortField;
    @ApiModelProperty("排序方式")
    private String sortType;
    @ApiModelProperty("链单状态名")
    private String status_tab;
    @ApiModelProperty("网络流转信息")
    private ScfpNetwork scfpNetwork;
}
