package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.UserInfoEntity;
import com.belazy.business.common.service.IUserInfoService;
import com.belazy.business.common.mapper.IUserInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 用户信息表 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@Service
@Transactional
public class UserInfoService  extends ServiceImpl<IUserInfoMapper, UserInfoEntity> implements IUserInfoService {

}
