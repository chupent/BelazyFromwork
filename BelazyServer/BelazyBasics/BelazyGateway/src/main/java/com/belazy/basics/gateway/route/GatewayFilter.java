package com.belazy.basics.gateway.route;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 过滤器定义模型
 *
 * @author tangcp
 */
@Data
public class GatewayFilter {

	/**
	 * 过滤器对应的Name
	 */
	private String name;

	/**
	 * 对应的路由规则
	 */
	private Map<String, String> args = new LinkedHashMap<>();
}
