package com.belazy.business.api.dto;

import java.util.Date;
import com.belazy.library.model.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 系统-参数配置表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:59:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description="系统-参数配置表")
public class SysConfigDTO  extends PageDTO{
	@ApiModelProperty(value = "参数键名")
	private String configKey;
	@ApiModelProperty(value = "参数名称")
	private String configName;
	@ApiModelProperty(value = "系统内置（Y是 N否）")
	private String configType;
	@ApiModelProperty(value = "参数键值")
	private String configValue;
	@ApiModelProperty(value = "创建者")
	private String createBy;
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	@ApiModelProperty(value = "主键")
	private String id;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "更新者")
	private String updateBy;
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
}
