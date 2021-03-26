package com.belazy.basics.auth.config;

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
        accessTokenConverter.setSigningKey ("$2a$10$FIypxkWFK3z2RQLxM86.Ku2oQ7WtFcV3a2FIW8F68VoralDT2fwxa");//设置秘钥(BelazySecret.JWT)
        return accessTokenConverter;
    }
}
