package com.woniuxy.commons.entity.DTO;

import com.woniuxy.commons.entity.ScfpEnterprise;
import com.woniuxy.commons.entity.ScfpNetwork;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.commons.entity.DTO
 * @Author qfx
 * @CreateTime 2022-06-07  16:32
 * @Description 流转网络数据传输实体类
 * @Version 1.0
 */
@ApiModel(description = "流转网络数据传输实体类")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NetworkDTO {
    @ApiModelProperty("融资机构（银行）")
    private String ename;
    @ApiModelProperty("链单名称")
    private String nname;
    @ApiModelProperty("参与企业数量")
    private int num;
    @ApiModelProperty("参与企业")
    private List<ScfpEnterprise> enterprises;
    @ApiModelProperty("参与企业id")
    private List<Integer> eids;
    @ApiModelProperty("当前页数")
    private int currentPage;
    @ApiModelProperty("每页条数")
    private int pageSize;
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
