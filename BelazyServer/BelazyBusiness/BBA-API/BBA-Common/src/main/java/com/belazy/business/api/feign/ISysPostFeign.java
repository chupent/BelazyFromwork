package com.belazy.business.api.feign;

import com.belazy.business.api.feign.fallback.SysPostFeignFallBack;

import org.springframework.cloud.openfeign.FeignClient;



/**
 * 岗位信息表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@FeignClient(value = "bbs-common",fallback = SysPostFeignFallBack.class)
public interface ISysPostFeign {

}