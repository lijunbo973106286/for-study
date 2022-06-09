package com.woniuxy.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-06 17:22
 * \* @Description：
 */
@ApiModel(description = "企业信息实体类")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScfpEnterprise {
    @ApiModelProperty("企业id")
    private int id;
    @ApiModelProperty("企业名称")
    private String name;
    @ApiModelProperty("支付密码")
    private String pay_pass;
    @ApiModelProperty("企业社会信用代码")
    private String credit_code;
    @ApiModelProperty("企业法人")
    private String legal_person;
    @ApiModelProperty("法人身份证")
    private String id_card;
    @ApiModelProperty("企业地址")
    private String address;
    @ApiModelProperty("企业银行账户id")
    private int account_id;
    @ApiModelProperty("业务角色类型")
    private int role;
    @ApiModelProperty("授信额度")
    private BigDecimal money;
    @ApiModelProperty("可用额度")
    private BigDecimal surplus;
    @ApiModelProperty("状态")
    private String status;
}
