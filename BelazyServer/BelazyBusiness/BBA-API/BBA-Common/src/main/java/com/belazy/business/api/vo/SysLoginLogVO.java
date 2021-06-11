package com.belazy.business.api.vo;;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

import java.io.Serializable;
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
public class SysLoginLogVO implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "浏览器类型")
    private String browser;
    @ApiModelProperty(value = "创建者")
    private String createBy;
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "登录IP地址")
    private String ipaddr;
    @ApiModelProperty(value = "登录地点")
    private String loginLocation;
    @ApiModelProperty(value = "访问时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    @ApiModelProperty(value = "用户账号")
    private String userName;

}
