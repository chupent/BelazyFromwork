package com.belazy.basics.auth.service;

import com.belazy.basics.auth.mapper.IUserMapper;
import com.belazy.basics.auth.model.UserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

/**
 * 自定义的用户信息服务类，Oauth 的用户需要有个ROLE_USER 角色才可以访问，所以这里写死
 *
 * @author tangcp
 */
@Slf4j
public class IUserDetailService implements UserDetailsService {
    @Resource
    private IUserMapper iUserMapper;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetail detail = iUserMapper.findUserByAccount (username);
        log.info ("UserDetail:{}", detail);
        return User.withUserDetails (detail).build ();
    }

    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        UserDetail detail = iUserMapper.findUserByMobile (mobile);
        log.info ("UserDetail:{}", detail);
        return User.withUserDetails (detail).build ();
    }
}
