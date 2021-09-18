package com.belazy.basics.auth.config;


import com.belazy.basics.auth.constant.SecurityConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * JWT配置类
 *
 * @author tangcp
 */
@Configuration
public class JwtTokenConfig {
    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore (jwtAccessTokenConverter ());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter ();
        accessTokenConverter.setSigningKey (SecurityConstants.SIGN_KEY);//设置秘钥(BelazySecret.JWT)
        return accessTokenConverter;
    }
}
