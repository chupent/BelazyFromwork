package com.belazy.basics.auth.config;

import com.belazy.basics.auth.constant.SecurityConstants;
import com.belazy.basics.auth.mobile.MobileSMSCodeAuthenticationProvider;
import com.belazy.basics.auth.service.IUserDetailService;
import com.belazy.basics.auth.service.impl.UserDetailService;
import com.belazy.library.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * OAuth2 依赖于 Security 的配置，需要配置一下Security
 *
 * @author tangcp
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    final private RedisService redisService;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring ().antMatchers (SecurityConstants.IGNORING_RESOURCES);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config =
                http.requestMatchers ().anyRequest ().and ().authorizeRequests ();
        config
                .antMatchers (SecurityConstants.OPEN_API).permitAll ()
                .anyRequest ()
                .authenticated ()
                .and ().csrf ().disable ();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider (authenticationProvider ())
                .userDetailsService (userDetailsService ())
                .passwordEncoder (passwordEncoder ());
    }

    @Bean//授权管理器
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean ();
    }

    @Bean//注入密码加密
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder ();
    }

    @Bean//注入密码加密
    public IUserDetailService userDetailsService() {
        return new UserDetailService ();
    }

    @Bean//认证提供者
    public MobileSMSCodeAuthenticationProvider authenticationProvider() {
        MobileSMSCodeAuthenticationProvider provider = new MobileSMSCodeAuthenticationProvider ();
        provider.setIUserDetailService (userDetailsService ());
        provider.setRedisService (redisService);
        return provider;
    }
}
