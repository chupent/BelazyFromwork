package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.SysUserInfoEntity;
import com.belazy.business.common.service.ISysUserInfoService;
import com.belazy.business.common.mapper.ISysUserInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 用户信息表 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@Service
@Transactional
public class SysUserInfoService  extends ServiceImpl<ISysUserInfoMapper, SysUserInfoEntity> implements ISysUserInfoService {

}
