package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.SysLoginLogEntity;
import com.belazy.business.common.service.ISysLoginLogService;
import com.belazy.business.common.mapper.ISysLoginLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 系统-访问记录 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@Service
@Transactional
public class SysLoginLogService  extends ServiceImpl<ISysLoginLogMapper, SysLoginLogEntity> implements ISysLoginLogService {

}
