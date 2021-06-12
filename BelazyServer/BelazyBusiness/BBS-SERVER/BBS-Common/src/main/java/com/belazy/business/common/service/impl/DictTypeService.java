package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.DictTypeEntity;
import com.belazy.business.common.service.IDictTypeService;
import com.belazy.business.common.mapper.IDictTypeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 系统-字典类型 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@Service
@Transactional
public class DictTypeService  extends ServiceImpl<IDictTypeMapper, DictTypeEntity> implements IDictTypeService {

}
