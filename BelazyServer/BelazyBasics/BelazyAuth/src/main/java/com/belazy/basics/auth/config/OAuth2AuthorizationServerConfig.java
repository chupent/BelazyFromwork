package com.belazy.basics.auth.config;

import com.belazy.basics.auth.exception.IOAuth2WebResponseExceptionTranslator;
import com.belazy.basics.auth.mobile.MobileSMSCodeTokenGranter;
import com.belazy.basics.auth.model.UserDetail;
import com.belazy.basics.auth.model.vo.UserInfoVo;
import com.belazy.basics.auth.service.IUserDetailService;
import com.belazy.library.constant.SecurityConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.*;

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
@Slf4j
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final JwtAccessTokenConverter jwtAccessTokenConverter;
    private final IUserDetailService iUserDetailService;
    private final AuthenticationManager authenticationManager;
    private final IOAuth2WebResponseExceptionTranslator exceptionTranslator;
    private final RedisConnectionFactory redisConnectionFactory;
    private final DataSource dataSource;
    @Bean
    public TokenStore redisTokenStore() {
        return new RedisTokenStore (redisConnectionFactory);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        // 把jwt增强，与额外信息增强加入到增强链
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter));
        endpoints
                .tokenStore (redisTokenStore())
                .accessTokenConverter(jwtAccessTokenConverter)
                .userDetailsService (iUserDetailService) // 用户信息得服务，一版是都数据库
                .tokenEnhancer (tokenEnhancerChain) //jwt增强
                .authenticationManager (authenticationManager)// 认证管理器。
                .allowedTokenEndpointRequestMethods (HttpMethod.GET, HttpMethod.POST);
        endpoints.tokenGranter (new CompositeTokenGranter (getTokenGranters (endpoints)));//重新这种之定义的Token生成器
        endpoints.exceptionTranslator (exceptionTranslator);//配置异常转换器
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //数据库配置
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clientDetailsService.setSelectClientDetailsSql(SecurityConstants.DEFAULT_FIND_STATEMENT_BY_CLIENT_ID);
        clientDetailsService.setFindClientDetailsSql(SecurityConstants.DEFAULT_FIND_STATEMENT);
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients ()//允许表单登录
                .tokenKeyAccess ("permitAll()") //开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()");
    }

    //Token增强
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (oAuth2AccessToken, oAuth2Authentication) -> {
            // 添加额外信息的map
            final Map<String, Object> additionMessage = new HashMap<> (2);
            // 获取当前登录的用户
            Object o =  oAuth2Authentication.getUserAuthentication ().getPrincipal ();
            if(o!=null){
                UserDetail detail = (UserDetail) o;
                UserInfoVo infoVo =new UserInfoVo ();
                infoVo.setAccount (detail.getAccount ());
                infoVo.setRoles (detail.getRoles ());
                infoVo.setId (detail.getId ());
                infoVo.setEmail (detail.getEmail ());
                infoVo.setPhoneNumber (detail.getPhoneNumber ());
                additionMessage.put(SecurityConstants.USER_INFO, infoVo);
            }
            ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(additionMessage);
            return oAuth2AccessToken;
        };
    }
    //自定义认证模式
    private List<TokenGranter> getTokenGranters(AuthorizationServerEndpointsConfigurer endpoints) {
        AuthorizationServerTokenServices tokenServices = endpoints.getTokenServices ();
        AuthorizationCodeServices authorizationCodeServices = endpoints.getAuthorizationCodeServices ();
        ClientDetailsService clientDetailsService = endpoints.getClientDetailsService ();
        OAuth2RequestFactory requestFactory = endpoints.getOAuth2RequestFactory ();
        List<TokenGranter> tokenGranters = new ArrayList<> ();
        tokenGranters.add (new AuthorizationCodeTokenGranter (tokenServices, authorizationCodeServices, clientDetailsService, requestFactory));
        tokenGranters.add (new RefreshTokenGranter (tokenServices, clientDetailsService, requestFactory));
        tokenGranters.add (new ImplicitTokenGranter (tokenServices, clientDetailsService, requestFactory));
        tokenGranters.add (new ClientCredentialsTokenGranter (tokenServices, clientDetailsService, requestFactory));
        if (authenticationManager != null) {
            tokenGranters.add (new ResourceOwnerPasswordTokenGranter (authenticationManager, tokenServices, clientDetailsService, requestFactory));
            tokenGranters.add (new MobileSMSCodeTokenGranter (authenticationManager, tokenServices, clientDetailsService, requestFactory));
        }
        return tokenGranters;
    }
}
