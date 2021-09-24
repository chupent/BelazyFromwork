package com.belazy.business.api.feign;

import com.belazy.business.api.feign.fallback.RoleFeignFallBack;
import com.belazy.library.model.constant.ApplicationNameConstant;
import org.springframework.cloud.openfeign.FeignClient;



/**
 * 角色信息表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:22:22
 */
@FeignClient(value = ApplicationNameConstant.BBS_COMMON,fallback = RoleFeignFallBack.class)
public interface IRoleFeign {

}