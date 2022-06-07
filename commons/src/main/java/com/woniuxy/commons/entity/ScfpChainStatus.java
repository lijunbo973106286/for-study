package com.woniuxy.commons.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-06 17:20
 * \* @Description：
 */
@ApiModel(description = "链单状态实体类")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScfpChainStatus {
    @ApiModelProperty("链单状态id")
    private int id;
    @ApiModelProperty("链单状态")
    private String status;
    @ApiModelProperty("当前页")
    private int currentPage;
    @ApiModelProperty("每页条数")
    private int pageSize;
}
