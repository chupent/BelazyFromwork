package com.belazy.business.api.dto;

import com.belazy.library.model.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 系统-大区
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description="系统-大区")
public class DistrictDTO  extends PageDTO{
	@ApiModelProperty(value = "编号")
	private String code;
	@ApiModelProperty(value = "主键ID")
	private Long id;
	@ApiModelProperty(value = "大区名称")
	private String name;
}
