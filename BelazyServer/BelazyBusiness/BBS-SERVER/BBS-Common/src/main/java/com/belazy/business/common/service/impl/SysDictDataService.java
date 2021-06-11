package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.SysDictDataEntity;
import com.belazy.business.common.service.ISysDictDataService;
import com.belazy.business.common.mapper.ISysDictDataMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 系统-通用字典表 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@Service
@Transactional
public class SysDictDataService  extends ServiceImpl<ISysDictDataMapper, SysDictDataEntity> implements ISysDictDataService {

}
