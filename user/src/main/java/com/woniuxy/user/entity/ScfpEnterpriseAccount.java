package com.woniuxy.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-06 17:30
 * \* @Description：
 */
@ApiModel(description = "企业信息实体类")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScfpEnterpriseAccount {
    @ApiModelProperty("企业账户id")
    private int id;
    @ApiModelProperty("银行名称（XX银行）")
    private String bank;
    @ApiModelProperty("银行开户行（XX支行）")
    private String name;
    @ApiModelProperty("银行账号")
    private String num;
    @ApiModelProperty("银行类型")
    private String type;
    @ApiModelProperty("开户地址")
    private String address;
    @ApiModelProperty("开户凭证")
    private int file_id;
    @ApiModelProperty("企业账户状态")
    private String status;
    @ApiModelProperty("对应企业id")
    private int enterprisid;
}
