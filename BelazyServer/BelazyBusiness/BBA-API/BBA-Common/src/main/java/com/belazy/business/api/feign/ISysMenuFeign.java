package com.belazy.business.api.feign;

import com.belazy.business.api.feign.fallback.SysMenuFeignFallBack;

import org.springframework.cloud.openfeign.FeignClient;



/**
 * 系统-菜单表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@FeignClient(value = "bbs-common",fallback = SysMenuFeignFallBack.class)
public interface ISysMenuFeign {

}