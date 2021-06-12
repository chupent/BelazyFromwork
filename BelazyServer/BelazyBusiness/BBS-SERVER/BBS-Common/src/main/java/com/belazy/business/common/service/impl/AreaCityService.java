package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.AreaCityEntity;
import com.belazy.business.common.service.IAreaCityService;
import com.belazy.business.common.mapper.IAreaCityMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 系统-省份城市区域表 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@Service
@Transactional
public class AreaCityService  extends ServiceImpl<IAreaCityMapper, AreaCityEntity> implements IAreaCityService {

}
