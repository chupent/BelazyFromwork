package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.SysDistrictAreaCityEntity;
import com.belazy.business.common.service.ISysDistrictAreaCityService;
import com.belazy.business.common.mapper.ISysDistrictAreaCityMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 系统-大区-省份关联表 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:58:12
 */
@Service
@Transactional
public class SysDistrictAreaCityService  extends ServiceImpl<ISysDistrictAreaCityMapper, SysDistrictAreaCityEntity> implements ISysDistrictAreaCityService {

}
