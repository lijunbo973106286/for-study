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
    private String bank;
    @ApiModelProperty("链单参与企业范围设置方式")
    private String set_way;
    @ApiModelProperty("当前页")
    private int currentPage;
    @ApiModelProperty("每页条数")
    private int pageSize;
}
