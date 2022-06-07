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
@ApiModel(description = "供应链实体类")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScfpSupply {
    @ApiModelProperty("供应商id（enterprise中的id）")
    private int supply_id;
    @ApiModelProperty("供应商的上游企业id")
    private int fid;
}
