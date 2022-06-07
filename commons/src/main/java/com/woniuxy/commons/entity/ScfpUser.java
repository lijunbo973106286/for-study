package com.woniuxy.commons.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-06 17:51
 * \* @Description：
 */
@ApiModel(description = "角色实体类")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScfpUser {
    @ApiModelProperty("用户id")
    private int id;
    @ApiModelProperty("登录名")
    private String uname;
    @ApiModelProperty("用户密码")
    String password;
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("用户身份证")
    private String identity;
    @ApiModelProperty("用户手机号")
    private String phonenum;
    @ApiModelProperty("用户邮箱")
    private String email;
    @ApiModelProperty("用户所属企业id")
    private int enterprise_id;
    @ApiModelProperty("状态")
    private String status;
    @ApiModelProperty("用户角色id")
    private int role_id;
    @ApiModelProperty("管理员id")
    int fid;

}
