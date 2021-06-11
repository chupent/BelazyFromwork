package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.SysDictTypeEntity;
import com.belazy.business.common.service.ISysDictTypeService;
import com.belazy.business.common.mapper.ISysDictTypeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 系统-字典类型 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@Service
@Transactional
public class SysDictTypeService  extends ServiceImpl<ISysDictTypeMapper, SysDictTypeEntity> implements ISysDictTypeService {

}
