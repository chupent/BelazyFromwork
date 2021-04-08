package com.belazy.business.common.controller.feign;

import com.belazy.business.api.feign.ISysBelazyUserFeign;
import com.belazy.business.api.vo.UserVo;
import com.belazy.library.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author tangcp
 */
@Slf4j
@ApiIgnore
@RestController
public class SysBelazyUserFeign implements ISysBelazyUserFeign {

    @Override
    public Result<UserVo> getUserVoById(String userId) {
        UserVo userVo = new UserVo ();
        userVo.setAccount ("123456789");
        userVo.setId ("1000");
        userVo.setName ("唐拨虎");
        userVo.setNickname ("唐龑");
        userVo.setPhone ("9527");
        return Result.success (userVo);
    }
}
