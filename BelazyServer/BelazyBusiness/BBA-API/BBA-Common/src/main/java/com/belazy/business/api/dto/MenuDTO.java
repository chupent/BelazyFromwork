package com.belazy.business.api.dto;

import java.util.Date;
import com.belazy.library.model.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 系统-菜单表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description="系统-菜单表")
public class MenuDTO  extends PageDTO{
	@ApiModelProperty(value = "组件路径")
	private String component;
	@ApiModelProperty(value = "创建者")
	private String createBy;
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	@ApiModelProperty(value = "菜单图标")
	private String icon;
	@ApiModelProperty(value = "主键")
	private String id;
	@ApiModelProperty(value = "是否为外链（0是 1否）")
	private Integer isFrame;
	@ApiModelProperty(value = "菜单名称")
	private String menuName;
	@ApiModelProperty(value = "菜单类型（M目录 C菜单 F按钮）")
	private String menuType;
	@ApiModelProperty(value = "")
	private String method;
	@ApiModelProperty(value = "显示顺序")
	private Integer orderNum;
	@ApiModelProperty(value = "父菜单ID")
	private String parentId;
	@ApiModelProperty(value = "路由地址")
	private String path;
	@ApiModelProperty(value = "权限标识")
	private String perms;
	@ApiModelProperty(value = "1 系统 2 工厂")
	private Integer platform;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "更新者")
	private String updateBy;
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
	@ApiModelProperty(value = "菜单状态（0显示 1隐藏）")
	private String visible;
}
