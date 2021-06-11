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
 * 岗位信息表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description="岗位信息表")
@TableName("sys_post")
public class SysPostEntity implements Serializable{
	private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "创建者")
	private String createBy;
    @ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")	
	private Date createTime;
    @ApiModelProperty(value = "主键")
	@TableId(value = "id", type = IdType.ASSIGN_UUID)
	private String id;
    @ApiModelProperty(value = "岗位编码")
	private String postCode;
    @ApiModelProperty(value = "岗位名称")
	private String postName;
    @ApiModelProperty(value = "显示顺序")
	private Integer postSort;
    @ApiModelProperty(value = "备注")
	private String remark;
    @ApiModelProperty(value = "状态（0正常 1停用）")
	private String status;
    @ApiModelProperty(value = "更新者")
	private String updateBy;
    @ApiModelProperty(value = "更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")	
	private Date updateTime;

}
