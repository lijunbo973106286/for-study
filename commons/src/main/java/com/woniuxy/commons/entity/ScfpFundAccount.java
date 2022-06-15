package com.woniuxy.commons.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@ApiModel("资金账户")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScfpFundAccount {
    @ApiModelProperty("id")
    private int id;
    @ApiModelProperty("企业id")
    private int eid;
    @ApiModelProperty("用户账号")
    private String uaccount;
    @ApiModelProperty("企业名称")
    private String ename;
    @ApiModelProperty("账户余额")
    private BigDecimal residual;
    @ApiModelProperty("账户状态")
    private String status;
    @ApiModelProperty("资金账户")
    private String faccount;
    @ApiModelProperty("支付密码")
    private int pay_pass;


}
