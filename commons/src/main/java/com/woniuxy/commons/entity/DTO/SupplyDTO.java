package com.woniuxy.commons.entity.DTO;

import com.woniuxy.commons.entity.ScfpEnterprise;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.commons.entity
 * @Author qfx
 * @CreateTime 2022-06-07  11:29
 * @Description 供应链数据传输实体类
 * @Version 1.0
 */
@ApiModel(description = "供应链数据传输实体类")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SupplyDTO {
    @ApiModelProperty("企业编号")
    private int eid;
    @ApiModelProperty("企业名称")
    private String ename;
    @ApiModelProperty("管理员名称")
    private String uname;
    @ApiModelProperty("管理员电话")
    private String phonenum;
    @ApiModelProperty("企业状态")
    private String estatus;
    @ApiModelProperty("子企业")
    private List<ScfpEnterprise> enterprises;
    @ApiModelProperty("层数")
    private int tier;

}
