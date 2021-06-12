package com.belazy.business.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

/**
 * 系统-字典类型
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description="系统-字典类型")
@TableName("sys_dict_type")
public class DictTypeEntity implements Serializable{
	private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "创建人ID")
	private String createBy;
    @ApiModelProperty(value = "创建人名称")
	private String createName;
    @ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")	
	private Date createTime;
    @ApiModelProperty(value = "字典名称")
	private String dictName;
    @ApiModelProperty(value = "字典类型")
	private String dictType;
    @ApiModelProperty(value = "主键")
	@TableId(value = "id", type = IdType.ASSIGN_UUID)
	private String id;
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
