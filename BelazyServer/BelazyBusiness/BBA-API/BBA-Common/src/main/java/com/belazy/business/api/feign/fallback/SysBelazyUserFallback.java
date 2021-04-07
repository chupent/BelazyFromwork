package com.belazy.business.api.feign.fallback;

import com.belazy.business.api.feign.SysBelazyUserFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author tangcp
 */
@Slf4j
@Component
public class SysBelazyUserFallback implements SysBelazyUserFeign {
}
