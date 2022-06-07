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
@ApiModel(description = "流转网络实体类")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScfpNetwork {
    @ApiModelProperty("流转网络id")
    private int id;
    @ApiModelProperty("流转网络编号")
    private String network_no;
    @ApiModelProperty("业务产品类型")
    private String type;
    @ApiModelProperty("融资机构（银行）")
    private int bank_id;
    @ApiModelProperty("链单状态")
    private int status;
    @ApiModelProperty("链单名称")
    private String name;
}
