package com.woniuxy.commons.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-06 17:47
 * \* @Description：
 */
@ApiModel(description = "角色菜单实体类")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScfpRoleMenu {
    @ApiModelProperty("角色id")
    private int role_id;
    @ApiModelProperty("菜单id")
    private int menu_id;
}
