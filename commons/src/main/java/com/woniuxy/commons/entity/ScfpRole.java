package com.woniuxy.commons.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-06 17:44
 * \* @Description：
 */
@ApiModel(description = "角色实体类")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScfpRole {
    @ApiModelProperty("角色id")
    private int id;
    @ApiModelProperty("角色名称")
    private String name;
    @ApiModelProperty("角色状态")
    private String status;
    @ApiModelProperty("角色描述")
    private String description;
}
