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
 * 角色信息表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年07月25日 00:18:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description="角色信息表")
@TableName("sys_role")
public class RoleEntity implements Serializable{
	private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "创建者")
	private String createBy;
    @ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")	
	private Date createTime;
    @ApiModelProperty(value = "数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）")
	private String dataScope;
    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
	private String delFlag;
    @ApiModelProperty(value = "主键")
	@TableId(value = "id", type = IdType.ASSIGN_UUID)
	private String id;
    @ApiModelProperty(value = "备注")
	private String remark;
    @ApiModelProperty(value = "角色权限字符串")
	private String roleKey;
    @ApiModelProperty(value = "角色名称")
	private String roleName;
    @ApiModelProperty(value = "显示顺序")
	private Integer roleSort;
    @ApiModelProperty(value = "角色状态（0正常 1停用）")
	private String status;
    @ApiModelProperty(value = "更新者")
	private String updateBy;
    @ApiModelProperty(value = "更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")	
	private Date updateTime;

}
