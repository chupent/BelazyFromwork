package com.belazy.basics.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

/**
 * 自定义的用户信息服务类，Oauth 的用户需要有个ROLE_USER 角色才可以访问，所以这里写死
 *
 * @author tangcp
 */
public interface IUserDetailService extends UserDetailsService {
    UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException;
    UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;
}
