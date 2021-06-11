package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.SysUserRoleEntity;
import com.belazy.business.common.service.ISysUserRoleService;
import com.belazy.business.common.mapper.ISysUserRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 用户角色关联表 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:58:12
 */
@Service
@Transactional
public class SysUserRoleService  extends ServiceImpl<ISysUserRoleMapper, SysUserRoleEntity> implements ISysUserRoleService {

}
