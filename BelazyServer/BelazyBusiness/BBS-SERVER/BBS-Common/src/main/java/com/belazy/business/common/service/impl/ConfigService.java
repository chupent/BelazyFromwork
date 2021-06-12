package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.ConfigEntity;
import com.belazy.business.common.service.IConfigService;
import com.belazy.business.common.mapper.IConfigMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 系统-参数配置表 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@Service
@Transactional
public class ConfigService  extends ServiceImpl<IConfigMapper, ConfigEntity> implements IConfigService {

}
