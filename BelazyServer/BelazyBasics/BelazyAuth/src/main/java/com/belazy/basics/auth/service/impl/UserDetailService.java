package com.belazy.basics.auth.service.impl;

import com.belazy.basics.auth.mapper.IUserMapper;
import com.belazy.basics.auth.model.UserDetail;
import com.belazy.basics.auth.service.IUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

/**
 * @author tangcp
 */
@Slf4j
public class UserDetailService implements IUserDetailService {
    @Resource
    private IUserMapper iUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetail detail = iUserMapper.findUserByAccount (username);
        log.info ("UserDetail:{}", detail);
        return detail;
    }

    @Override
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        UserDetail detail = iUserMapper.findUserByMobile (mobile);
        log.info ("UserDetail:{}", detail);
        return detail;
    }

    @Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        UserDetail detail = iUserMapper.findUserByEmail (email);
        log.info ("UserDetail:{}", detail);
        return detail;
    }
}
