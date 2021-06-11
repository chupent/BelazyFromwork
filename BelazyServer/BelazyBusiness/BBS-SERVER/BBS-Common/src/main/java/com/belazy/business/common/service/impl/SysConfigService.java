package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.SysConfigEntity;
import com.belazy.business.common.service.ISysConfigService;
import com.belazy.business.common.mapper.ISysConfigMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 系统-参数配置表 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:59:55
 */
@Service
@Transactional
public class SysConfigService  extends ServiceImpl<ISysConfigMapper, SysConfigEntity> implements ISysConfigService {

}
