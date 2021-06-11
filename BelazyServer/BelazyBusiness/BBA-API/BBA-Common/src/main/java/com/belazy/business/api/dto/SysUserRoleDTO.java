package com.belazy.business.api.dto;

import com.belazy.library.model.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 用户角色关联表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:58:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description="用户角色关联表")
public class SysUserRoleDTO  extends PageDTO{
	@ApiModelProperty(value = "角色ID")
	private String roleId;
	@ApiModelProperty(value = "用户ID")
	private String userId;
}
