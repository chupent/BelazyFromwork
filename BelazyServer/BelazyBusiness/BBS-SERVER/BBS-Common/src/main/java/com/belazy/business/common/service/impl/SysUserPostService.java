package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.SysUserPostEntity;
import com.belazy.business.common.service.ISysUserPostService;
import com.belazy.business.common.mapper.ISysUserPostMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 用户与岗位关联表 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:58:12
 */
@Service
@Transactional
public class SysUserPostService  extends ServiceImpl<ISysUserPostMapper, SysUserPostEntity> implements ISysUserPostService {

}
