package com.belazy.library.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tangcp
 */
public abstract class BasicEntity implements Serializable {
    @ApiModelProperty(value = "创建人ID")
    private String createBy;
    @ApiModelProperty(value = "创建人名称")
    private String createName;
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @ApiModelProperty(value = "更新人ID")
    private String updateBy;
    @ApiModelProperty(value = "更新人名称")
    private String updateName;
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
