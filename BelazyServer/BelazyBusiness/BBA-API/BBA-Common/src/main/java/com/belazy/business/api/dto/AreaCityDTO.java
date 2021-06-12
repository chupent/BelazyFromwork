package com.belazy.business.api.dto;

import com.belazy.library.model.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 系统-省份城市区域表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description="系统-省份城市区域表")
public class AreaCityDTO  extends PageDTO{
	@ApiModelProperty(value = "代码")
	private String code;
	@ApiModelProperty(value = "主键ID")
	private Long id;
	@ApiModelProperty(value = "层级，1=省，2=市，3=区")
	private Integer lvl;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "上级代码")
	private String parentCode;
}
