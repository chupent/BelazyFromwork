package com.belazy.basics.auth.mobile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义短信验证码方式
 *
 * @author tangcp
 */
@Slf4j
public class MobileSMSCodeTokenGranter extends AbstractTokenGranter {
    private static final String GRANT_TYPE = "sms_code";
    private final AuthenticationManager authenticationManager;

    protected MobileSMSCodeTokenGranter(
            AuthenticationManager authenticationManager,
            AuthorizationServerTokenServices tokenServices,
            ClientDetailsService clientDetailsService,
            OAuth2RequestFactory requestFactory,
            String grantType) {
        super (tokenServices, clientDetailsService, requestFactory, grantType);
        this.authenticationManager = authenticationManager;
    }

    public MobileSMSCodeTokenGranter(
            AuthenticationManager authenticationManager,
            AuthorizationServerTokenServices tokenServices,
            ClientDetailsService clientDetailsService,
            OAuth2RequestFactory requestFactory) {
        this (authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
    }

    /***
     * 在这个方法可以进行验证码等其他操作
     * @param client
     * @param tokenRequest
     * @return
     */
    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        //获取参数
        Map<String, String> parameters = new LinkedHashMap<String, String> (tokenRequest.getRequestParameters ());
        MobileSMSCodeParam appParam = new MobileSMSCodeParam ();
        try {
            Field[] fields = appParam.getClass ().getDeclaredFields ();
            for (Field f : fields) {
                String val = parameters.get (f.getName ());
                if (StringUtils.isEmpty (val)) {
                    continue;
                } else {
                    f.setAccessible (true);
                    f.set (appParam, val);
                }
            }
        } catch (IllegalAccessException e) {
            log.error ("反射错误!");
        }
        parameters.remove ("password"); //短信验证码的登录不需要密码，删除
        log.info ("AppParam sysout:{}", appParam);

        Authentication userAuth = new MobileSMSCodeAuthenticationToken (appParam);
        ((AbstractAuthenticationToken) userAuth).setDetails (parameters);
        try {
            userAuth = this.authenticationManager.authenticate (userAuth);
        } catch (AccountStatusException var8) {
            throw new InvalidGrantException (var8.getMessage ());
        } catch (BadCredentialsException var9) {
            throw new InvalidGrantException (var9.getMessage ());
        }
        if (userAuth != null && userAuth.isAuthenticated ()) {
            OAuth2Request storedOAuth2Request = this.getRequestFactory ().createOAuth2Request (client, tokenRequest);
            return new OAuth2Authentication (storedOAuth2Request, userAuth);
        } else {
            throw new InvalidGrantException ("Could not authenticate user: " + appParam.getUsername ());
        }
    }
}
