package com.belazy.business.api.feign;

import com.belazy.business.api.feign.fallback.ConfigFeignFallBack;

import com.belazy.library.model.constant.AppcationNameConstant;
import org.springframework.cloud.openfeign.FeignClient;



/**
 * 系统-参数配置表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:22:22
 */
@FeignClient(value = AppcationNameConstant.BBS_COMMON,fallback = ConfigFeignFallBack.class)
public interface IConfigFeign {

}