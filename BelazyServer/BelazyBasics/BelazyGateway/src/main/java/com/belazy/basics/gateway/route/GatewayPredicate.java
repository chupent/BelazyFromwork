package com.belazy.basics.gateway.route;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 路由断言定义模型
 *
 * @author tangcp
 */
@Data
public class GatewayPredicate {

	/**
	 * 断言对应的Name
	 */
	private String name;

	/**
	 * 配置的断言规则
	 */
	private Map<String, String> args = new LinkedHashMap<>();
}
