package com.belazy.business.api.dto;

import java.util.Date;
import com.belazy.library.model.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 系统-文件信息表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description="系统-文件信息表")
public class FileDTO  extends PageDTO{
	@ApiModelProperty(value = "创建人id")
	private String createBy;
	@ApiModelProperty(value = "创建人")
	private String createName;
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	@ApiModelProperty(value = "主键")
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
	private Date updateTime;
}
