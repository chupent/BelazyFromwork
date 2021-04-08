package com.belazy.business.api.feign.fallback;

import com.belazy.business.api.feign.ISysBelazyUserFeign;
import com.belazy.business.api.vo.UserVo;
import com.belazy.library.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author tangcp
 */
@Slf4j
@Component
public class SysBelazyUserFallback implements ISysBelazyUserFeign {
    @Override
    public Result<UserVo> getUserVoById(String userId) {
        log.error ("userId=>{}",userId);
        return Result.fail ();
    }
}
