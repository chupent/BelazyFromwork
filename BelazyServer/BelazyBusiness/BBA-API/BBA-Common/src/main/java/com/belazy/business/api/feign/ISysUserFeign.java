package com.belazy.business.api.feign;

import com.belazy.business.api.feign.fallback.SysUserFeignFallBack;
import com.belazy.library.constant.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;



/**
 * 用户登录账号
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 13:04:11
 */
@FeignClient(value = AppConstant.BBS_COMMON,fallback = SysUserFeignFallBack.class)
public interface ISysUserFeign {

}