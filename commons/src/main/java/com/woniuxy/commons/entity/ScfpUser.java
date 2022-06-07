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
    @ApiModelProperty("用户姓名")
    private String name;
    @ApiModelProperty("用户角色id")
    private int role_id;
    @ApiModelProperty("用户身份证")
    private String id_card;
    @ApiModelProperty("用户联系方式")
    private String phonenum;
    @ApiModelProperty("用户邮箱")
    private String email;
    @ApiModelProperty("用户所属企业id")
    private int enterprise_id;
    @ApiModelProperty("状态")
    private String status;
    @ApiModelProperty("当前页")
    private int currentPage;
    @ApiModelProperty("每页条数")
    private int pageSize;
}
