package com.woniuxy.commons.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.commons.entity
 * @Author qfx
 * @CreateTime 2022-06-13  15:59
 * @Description 信用额度实体类
 * @Version 1.0
 */
@ApiModel(description = "信用额度实体类")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScfpAmount {
    @ApiModelProperty("被授信企业id")
    private int eid;
    @ApiModelProperty("授信机构id")
    private int bank_id;
    @ApiModelProperty("授信编号")
    private String id;
    @ApiModelProperty("授信额度")
    private BigDecimal total;
    @ApiModelProperty("可用额度")
    private BigDecimal available;
    @ApiModelProperty("额度起始日")
    private String start_time;
    @ApiModelProperty("额度到期日")
    private String end_time;
    @ApiModelProperty("状态")
    private int status;
    @ApiModelProperty("当前页")
    private int currentPage;
    @ApiModelProperty("每页条数")
    private int pageSize;
    @ApiModelProperty("授信机构名称")
    private String bname;
    @ApiModelProperty("授信总额度")
    private BigDecimal totalMoney;
    @ApiModelProperty("总可用额度")
    private BigDecimal totalAvailable;
}
