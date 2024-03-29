package com.belazy.business.api.dto;

import java.util.Date;
import com.belazy.library.model.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 部门信息表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description="部门信息表")
public class DeptDTO  extends PageDTO{
	@ApiModelProperty(value = "详细地址")
	private String address;
	@ApiModelProperty(value = "祖级列表")
	private String ancestors;
	@ApiModelProperty(value = "市")
	private String city;
	@ApiModelProperty(value = "创建者")
	private String createBy;
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	@ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
	private String delFlag;
	@ApiModelProperty(value = "部门名称")
	private String deptName;
	@ApiModelProperty(value = "机构类型:0总公司、1分公司、1门店渠道、3门店")
	private Integer deptType;
	@ApiModelProperty(value = "区")
	private String district;
	@ApiModelProperty(value = "邮箱")
	private String email;
	@ApiModelProperty(value = "主键")
	private String id;
	@ApiModelProperty(value = "负责人")
	private String leader;
	@ApiModelProperty(value = "显示顺序")
	private Integer orderNum;
	@ApiModelProperty(value = "父部门id")
	private String parentId;
	@ApiModelProperty(value = "联系电话")
	private String phone;
	@ApiModelProperty(value = "省")
	private String province;
	@ApiModelProperty(value = "大区")
	private String showArea;
	@ApiModelProperty(value = "部门状态（0正常 1停用）")
	private String status;
	@ApiModelProperty(value = "更新者")
	private String updateBy;
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
}
