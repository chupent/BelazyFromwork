package com.belazy.basics.gateway.properties;

import com.belazy.library.constant.AppConstant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.EnvironmentCapable;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.List;
/**
 * 配置文件
 *
 * @author FRANK
 */
@Getter
@Setter
@Configuration
public class EnvironmentProperties implements EnvironmentAware, EnvironmentCapable {

	@Nullable
	private Environment environment;

	/**
	 * 环境，方便在代码中获取
	 * @return 环境 env
	 */
	public String getEnv() {
		String[] activeProfiles = environment.getActiveProfiles();
		// 判断环境:dev、test、prod
		List<String> activeProfileList = Arrays.asList(activeProfiles);
		if (activeProfileList.isEmpty()) {
			return AppConstant.DEV_CODE;// 默认dev开发
		} else {
			return activeProfileList.get(0);
		}
	}

	/**
	 * 应用名称${spring.application.name}
	 * @return 应用名
	 */
	public String getName() {
		return environment.getProperty("spring.application.name", "");
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public Environment getEnvironment() {
		return this.environment;
	}
}
