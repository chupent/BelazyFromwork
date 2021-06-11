package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.SysAreaCityEntity;
import com.belazy.business.common.service.ISysAreaCityService;
import com.belazy.business.common.mapper.ISysAreaCityMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 系统-省份城市区域表 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:27:25
 */
@Service
@Transactional
public class SysAreaCityService  extends ServiceImpl<ISysAreaCityMapper, SysAreaCityEntity> implements ISysAreaCityService {

}
