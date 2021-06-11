package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.SysUserEntity;
import com.belazy.business.common.service.ISysUserService;
import com.belazy.business.common.mapper.ISysUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 用户登录账号 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 13:04:11
 */
@Service
@Transactional
public class SysUserService  extends ServiceImpl<ISysUserMapper, SysUserEntity> implements ISysUserService {

}
