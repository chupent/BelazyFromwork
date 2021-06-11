package com.belazy.business.api.vo;;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
/**
 * 系统-通用字典表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description="系统-通用字典表")
public class SysDictDataVO implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "创建人ID")
    private String createBy;
    @ApiModelProperty(value = "创建人名称")
    private String createName;
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @ApiModelProperty(value = "字典类型说明")
    private String dictDesc;
    @ApiModelProperty(value = "字典标签")
    private String dictLabel;
    @ApiModelProperty(value = "字典排序")
    private Integer dictSort;
    @ApiModelProperty(value = "字典类型")
    private String dictType;
    @ApiModelProperty(value = "字典值")
    private String dictValue;
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "父类ID")
    private String pid;
    @ApiModelProperty(value = "状态：0-启用，1-停用")
    private Integer state;
    @ApiModelProperty(value = "更新人ID")
    private String updateBy;
    @ApiModelProperty(value = "更新人名称")
    private String updateName;
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
