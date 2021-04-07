package com.belazy.business.consumer.controller;

import com.belazy.business.api.feign.SysBelazyUserFeign;
import com.belazy.library.core.basics.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangcp
 */
@RestController
@Api(tags = "用户信息相关")
public class SysBelazyUserController extends BaseController implements SysBelazyUserFeign {
}
