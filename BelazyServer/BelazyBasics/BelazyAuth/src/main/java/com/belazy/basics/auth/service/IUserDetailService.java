package com.belazy.basics.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
/**
 * 自定义的用户信息服务类，Oauth 的用户需要有个  ROLE_USER 角色才可以访问，所以这里写死
 *
 * @author tangcp
 */
@Slf4j
public class IUserDetailService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info ("表单登录用户名:" + username);
        // 根据用户名查找用户信息
        //根据查找到的用户信息判断用户是否被冻结
        String password = passwordEncoder.encode ("123456");
        log.info ("数据库密码是:" + password);
        return new User (username, password, true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList ("admin,ROLE_USER"));
    }
}
