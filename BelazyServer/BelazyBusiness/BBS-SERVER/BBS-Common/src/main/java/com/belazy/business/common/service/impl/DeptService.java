package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.DeptEntity;
import com.belazy.business.common.service.IDeptService;
import com.belazy.business.common.mapper.IDeptMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 部门信息表 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@Service
@Transactional
public class DeptService  extends ServiceImpl<IDeptMapper, DeptEntity> implements IDeptService {

}
