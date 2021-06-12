package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.UserEntity;
import com.belazy.business.common.service.IUserService;
import com.belazy.business.common.mapper.IUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 用户登录账号 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@Service
@Transactional
public class UserService  extends ServiceImpl<IUserMapper, UserEntity> implements IUserService {

}
