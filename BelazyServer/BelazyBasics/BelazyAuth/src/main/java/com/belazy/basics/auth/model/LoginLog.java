package com.belazy.basics.auth.model;

import lombok.Data;

/**
 * @author tangcp
 */
@Data
public class LoginLog {
    private String source;//登录来源:App手机应用 Web应用
    private String userName;//登录名称
    private String ipaddr;//登录IP
    private String loginLocation;//登录地
    private String os;//操作系统
    private String browser;//设备版本(华为10)|浏览器(类型+版本)
    private String deviceId;//设备唯一标识（手机设备ID,电脑为MAC地址)
    private String version;//APP版本，web不需要记录
    private String status;//登录状态（0成功 1失败）
    private String msg;
    private String createBy;
    private String updateBy;
}
