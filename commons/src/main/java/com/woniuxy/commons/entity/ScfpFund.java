package com.woniuxy.commons.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-06 17:30
 * \* @Description：
 */
@ApiModel("附件实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScfpFund {
    @ApiModelProperty("资金产品id")
    private int id;
    @ApiModelProperty("资金产品名称")
    private String fund_name;
    @ApiModelProperty("资金产品利息")
    private BigDecimal fund_interest;
    @ApiModelProperty("当前页")
    private int currentPage;
    @ApiModelProperty("每页条数")
    private int pageSize;
}
