package com.belazy.business.api.dto;

import com.belazy.library.model.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 系统-大区-省份关联表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:58:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description="系统-大区-省份关联表")
public class SysDistrictAreaCityDTO  extends PageDTO{
	@ApiModelProperty(value = "主键ID")
	private Long id;
	@ApiModelProperty(value = "省份城市区域表id")
	private Long sysAreaCityId;
	@ApiModelProperty(value = "大区表ID")
	private Long sysDistrictId;
}
