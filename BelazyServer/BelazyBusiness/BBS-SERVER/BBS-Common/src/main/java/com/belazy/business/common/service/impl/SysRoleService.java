package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.SysRoleEntity;
import com.belazy.business.common.service.ISysRoleService;
import com.belazy.business.common.mapper.ISysRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 角色信息表 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@Service
@Transactional
public class SysRoleService  extends ServiceImpl<ISysRoleMapper, SysRoleEntity> implements ISysRoleService {

}
