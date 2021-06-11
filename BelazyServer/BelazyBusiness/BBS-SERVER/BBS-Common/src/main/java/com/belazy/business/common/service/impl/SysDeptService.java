package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.SysDeptEntity;
import com.belazy.business.common.service.ISysDeptService;
import com.belazy.business.common.mapper.ISysDeptMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 部门信息表 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@Service
@Transactional
public class SysDeptService  extends ServiceImpl<ISysDeptMapper, SysDeptEntity> implements ISysDeptService {

}
