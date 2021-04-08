package com.belazy.business.api.feign;

import com.belazy.business.api.feign.fallback.SysBelazyUserFallback;
import com.belazy.business.api.vo.UserVo;
import com.belazy.library.constant.AppConstant;
import com.belazy.library.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tangcp
 */
@FeignClient(value = AppConstant.BBS_COMMON,fallback = SysBelazyUserFallback.class)
public interface ISysBelazyUserFeign {
    @GetMapping("/feign/getUserVoById")
    Result<UserVo> getUserVoById(@RequestParam("userId") String userId);
}
