package com.belazy.business.api.feign;

import com.belazy.business.api.feign.fallback.UserFeignFallBack;

import com.belazy.library.model.constant.AppcationNameConstant;
import org.springframework.cloud.openfeign.FeignClient;



/**
 * 用户登录账号
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:08:03
 */
@FeignClient(value = AppcationNameConstant.BBS_COMMON,fallback = UserFeignFallBack.class)
public interface IUserFeign {

}