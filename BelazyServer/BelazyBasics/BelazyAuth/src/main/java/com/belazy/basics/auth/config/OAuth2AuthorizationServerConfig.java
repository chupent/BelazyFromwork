package com.belazy.basics.auth.config;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * 认证服务器配置
 * 授权码模式->
 * 1、访问固定连接 http://localhost:8000/oauth/authorize?client_id=wuzzClientId&response_type=code&redirect_uri=http://www.baidu.com&scope=all
 * 2、未登录将会跳转到用户登录页面进行登录
 * 3、然后将会跳转到下面这个页面。询问用户是否为 wuzzClientId这个应用授权
 * 4、点击授权，将会跳转到回调地址页（地址需备案），由于没有备案域名，这里直接跳到百度，授权码code会携带在连接中
 * 5、使用授权码换取 access_token
 * <p>
 * 密码模式->
 * 只需要修改授权类型，增加 用户名、密码
 *
 * @author tangcp
 */
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    final private TokenStore tokenStore;
    final private JwtAccessTokenConverter jwtAccessTokenConverter;
    final private UserDetailsService userDetailsService;
    final private AuthenticationManager authenticationManager;
    final private PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints){
        endpoints
                .tokenStore (tokenStore)
                .accessTokenConverter (jwtAccessTokenConverter)
                .userDetailsService (userDetailsService) // 用户信息得服务，一版是都数据库
                .authenticationManager (authenticationManager)// 认证管理器。
                .allowedTokenEndpointRequestMethods (HttpMethod.GET, HttpMethod.POST);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory () //Token保存在内存中
                .withClient ("wuzzClientId")//客户端得ID,比如我们在QQ互联中心申请得。可以写多个。配置 循环
                .secret (passwordEncoder.encode ("wuzzSecret")) // 客户端密钥，需要进行加密
                .accessTokenValiditySeconds (7200)// token 有效时常  0 永久有效
                .authorizedGrantTypes ("password", "refresh_token", "authorization_code")// 支持得授权类型:支持刷新令牌、密码模式、授权码模式
                .scopes ("all", "read", "write")//拥有的 scope  可选
                .redirectUris ("http://www.baidu.com");//回调地址
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security){
        security.allowFormAuthenticationForClients ()//允许表单登录
                .checkTokenAccess ("permitAll()"); //开启/oauth/check_token验证端口认证权限访问
    }

}
