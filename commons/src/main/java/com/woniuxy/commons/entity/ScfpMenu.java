package com.woniuxy.commons.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-06 17:07
 * \* @Description：
 */
@ApiModel(description = "菜单实体类")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScfpMenu {

    @ApiModelProperty("菜单id")
    private int id;
    @ApiModelProperty("菜单名称")
    private String menuname;
    @ApiModelProperty("菜单路径")
    private String url;
    @ApiModelProperty("父级菜单id")
    private int fid;
    @ApiModelProperty("子菜单")
    private List<ScfpMenu> menus;
}
