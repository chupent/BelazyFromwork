package com.belazy.business.api.feign;

import com.belazy.business.api.feign.fallback.SysAreaCityFeignFallBack;

import org.springframework.cloud.openfeign.FeignClient;



/**
 * 系统-省份城市区域表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:27:25
 */
@FeignClient(value = "bbs-common",fallback = SysAreaCityFeignFallBack.class)
public interface ISysAreaCityFeign {

}