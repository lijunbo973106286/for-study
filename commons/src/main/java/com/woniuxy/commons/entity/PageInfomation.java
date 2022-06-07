package com.woniuxy.commons.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.commons.entity
 * @Author qfx
 * @CreateTime 2022-06-07  11:14
 * @Description 分页信息实体类
 * @Version 1.0
 */
@ApiModel(description = "分页信息实体类")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageInfomation {
    @ApiModelProperty("当前页")
    private int currentPage;
    @ApiModelProperty("每页条数")
    private int pageSize;
}
