package com.woniuxy.commons.entity.DTO;

import com.woniuxy.commons.entity.ScfpEnterpriseAccount;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EnterpriseAccountDTO {
    //企业名称
    @ApiModelProperty("企业名称")
    private String ename;
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
    @ApiModelProperty("当前页")
    private int currentPage;
    @ApiModelProperty("每页条数")
    private int pageSize;
}
