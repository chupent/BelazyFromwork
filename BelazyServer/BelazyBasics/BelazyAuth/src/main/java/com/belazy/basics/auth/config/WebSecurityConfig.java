package com.belazy.basics.auth.config;

import com.belazy.basics.auth.service.IUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * OAuth2 依赖于 Security 的配置，需要配置一下Security
 * @author tangcp
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").fullyAuthenticated().and().httpBasic();
    }
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean//注入密码加密
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder ();
    }
    @Bean//注入密码加密
    public UserDetailsService userDetailsService() {
        return new IUserDetailService ();
    }
}
