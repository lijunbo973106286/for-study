package com.woniuxy.commons.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-06 17:30
 * \* @Description：
 */
@ApiModel("附件实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScfpFile {
    @ApiModelProperty("文件id")
    private int id;
    @ApiModelProperty("文件名称")
    private String file_name;
    @ApiModelProperty("文件路径")
    private String file_url;
    @ApiModelProperty("状态(是否删除)")
    private String status;
    @ApiModelProperty("添加人")
    private String creator;
    @ApiModelProperty("添加时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date create_time;
    @ApiModelProperty("文件类型")
    private String file_type;
    @ApiModelProperty("当前页")
    private int currentPage;
    @ApiModelProperty("每页条数")
    private int pageSize;
    @ApiModelProperty("附件对应的链单id")
    private int chain_id;
}
