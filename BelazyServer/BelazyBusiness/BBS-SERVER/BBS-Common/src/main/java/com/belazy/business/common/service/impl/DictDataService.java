package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.DictDataEntity;
import com.belazy.business.common.service.IDictDataService;
import com.belazy.business.common.mapper.IDictDataMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 系统-通用字典表 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@Service
@Transactional
public class DictDataService  extends ServiceImpl<IDictDataMapper, DictDataEntity> implements IDictDataService {

}
