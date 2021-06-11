package com.belazy.business.api.dto;

import java.util.Date;
import com.belazy.library.model.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 系统-访问记录
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ApiModel(description="系统-访问记录")
public class SysLoginLogDTO  extends PageDTO{
	@ApiModelProperty(value = "浏览器类型")
	private String browser;
	@ApiModelProperty(value = "创建者")
	private String createBy;
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	@ApiModelProperty(value = "主键")
	private String id;
	@ApiModelProperty(value = "登录IP地址")
	private String ipaddr;
	@ApiModelProperty(value = "登录地点")
	private String loginLocation;
	@ApiModelProperty(value = "访问时间")
	private Date loginTime;
	@ApiModelProperty(value = "提示消息")
	private String msg;
	@ApiModelProperty(value = "操作系统")
	private String os;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "登录状态（0成功 1失败）")
	private String status;
	@ApiModelProperty(value = "更新者")
	private String updateBy;
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
	@ApiModelProperty(value = "用户账号")
	private String userName;
}
