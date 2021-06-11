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
 * 系统-文件信息表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description="系统-文件信息表")
@TableName("sys_file")
public class SysFileEntity implements Serializable{
	private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "创建人id")
	private String createBy;
    @ApiModelProperty(value = "创建人")
	private String createName;
    @ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")	
	private Date createTime;
    @ApiModelProperty(value = "主键")
	@TableId(value = "id", type = IdType.ASSIGN_UUID)
	private String id;
    @ApiModelProperty(value = "主路径(后期迁移数据使用)")
	private String mainPath;
    @ApiModelProperty(value = "文件名")
	private String name;
    @ApiModelProperty(value = "路径")
	private String path;
    @ApiModelProperty(value = "短路径")
	private String shortPath;
    @ApiModelProperty(value = "文件类型")
	private String type;
    @ApiModelProperty(value = "更新人id")
	private String updateBy;
    @ApiModelProperty(value = "更新人")
	private String updateName;
    @ApiModelProperty(value = "更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")	
	private Date updateTime;

}
