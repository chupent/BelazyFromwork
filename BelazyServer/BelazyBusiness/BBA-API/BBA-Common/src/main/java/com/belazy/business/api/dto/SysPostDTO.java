package com.belazy.business.api.dto;

import java.util.Date;
import com.belazy.library.model.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
public class SysPostDTO  extends PageDTO{
	@ApiModelProperty(value = "创建者")
	private String createBy;
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	@ApiModelProperty(value = "主键")
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
	private Date updateTime;
}
