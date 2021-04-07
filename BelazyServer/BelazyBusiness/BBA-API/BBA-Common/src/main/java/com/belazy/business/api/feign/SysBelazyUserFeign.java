package com.belazy.business.api.feign;

import com.belazy.business.api.feign.fallback.SysBelazyUserFallback;
import com.belazy.library.constant.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tangcp
 */
@FeignClient(value = AppConstant.BBS_COMMON,fallback = SysBelazyUserFallback.class)
public interface SysBelazyUserFeign {

}
