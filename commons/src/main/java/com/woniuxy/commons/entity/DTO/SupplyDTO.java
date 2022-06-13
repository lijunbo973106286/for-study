package com.woniuxy.commons.entity.DTO;

import com.sun.org.apache.xpath.internal.operations.Equals;
import com.woniuxy.commons.entity.ScfpEnterprise;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
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
    private String username;
    @ApiModelProperty("管理员电话")
    private String phonenum;
    @ApiModelProperty("企业状态")
    private String estatus;
    @ApiModelProperty("子企业")
    private List<SupplyDTO> enterprises;
    @ApiModelProperty("层数")
    private int tier;
    @ApiModelProperty("下级客户数量")
    private int num;
    @ApiModelProperty("企业地址")
    private String address;
    @ApiModelProperty("当前页")
    private int currentPage;
    @ApiModelProperty("每页条数")
    private int pageSize;
    @ApiModelProperty("邀请次数")
    private int count;
    @ApiModelProperty("最近邀请时间")
    private String update_time;
    @ApiModelProperty("当前企业id")
    private int fid;

}
