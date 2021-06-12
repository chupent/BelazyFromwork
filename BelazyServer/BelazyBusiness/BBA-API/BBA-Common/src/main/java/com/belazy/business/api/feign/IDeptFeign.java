package com.belazy.business.api.feign;

import com.belazy.business.api.feign.fallback.DeptFeignFallBack;

import org.springframework.cloud.openfeign.FeignClient;
import com.belazy.library.constant.AppConstant;


/**
 * 部门信息表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:22:22
 */
@FeignClient(value = AppConstant.BBS_COMMON,fallback = DeptFeignFallBack.class)
public interface IDeptFeign {

}