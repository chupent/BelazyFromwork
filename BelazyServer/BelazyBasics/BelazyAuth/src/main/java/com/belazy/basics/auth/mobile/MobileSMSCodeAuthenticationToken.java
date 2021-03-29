package com.belazy.basics.auth.mobile;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * 手机短信自定义接收TOKEN凭证
 *
 * @author tangcp
 */
public class MobileSMSCodeAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private Object principal;
    private Object credentials;

    public MobileSMSCodeAuthenticationToken(MobileSMSCodeParam param) {
        super (param, param.getSmsCode ());
        this.principal = param;
    }

    public MobileSMSCodeAuthenticationToken(Object credentials, MobileSMSCodeParam param) {
        super (param, credentials);
        this.credentials = credentials;
        this.principal = param;
    }

    public MobileSMSCodeAuthenticationToken(Object credentials, List<GrantedAuthority> authorities, MobileSMSCodeParam param) {
        super (param, credentials, authorities);
        this.credentials = credentials;
        this.principal = param;
    }

    public Object getCredentials() {
        return credentials;
    }

    public Object getPrincipal() {
        return principal;
    }
    /**获取入参**/
    public MobileSMSCodeParam getParam(){
        return null!=principal?(MobileSMSCodeParam) principal:null;
    }
}
