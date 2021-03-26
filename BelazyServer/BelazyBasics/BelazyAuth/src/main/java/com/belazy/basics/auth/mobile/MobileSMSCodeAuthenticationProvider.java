package com.belazy.basics.auth.mobile;

import com.belazy.library.core.constant.CommonConstant;
import com.belazy.library.redis.service.RedisService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 手机号+验证码验证提供者
 *
 * @author tangcp
 */
@Slf4j
public class MobileSMSCodeAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Setter
    private UserDetailsService userDetailsService;
    @Setter
    private RedisService redisService;

    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        MobileSMSCodeAuthenticationToken token = (MobileSMSCodeAuthenticationToken) authentication;
        MobileSMSCodeParam smsCodeParam = token.getParam ();
        log.info ("认证开始....");
        if(smsCodeParam==null){
            log.error ("MobileSMSCodeParam is null");
            throw new BadCredentialsException (this.messages.getMessage ("401", "Bad MobileSMSCodeParam"));
        }
    }

    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        UserDetails loadedUser;
        try {
            MobileSMSCodeAuthenticationToken token = (MobileSMSCodeAuthenticationToken) usernamePasswordAuthenticationToken;
            MobileSMSCodeParam param = token.getParam ();
            String smsCode= param.getSmsCode ();
            String username= param.getUsername ();
            Object obj = redisService.get(CommonConstant.LOGIN_SMS_CODE_KEY+username);
            if(null==smsCode||"".equals (smsCode)){
                throw  new BadCredentialsException(this.messages.getMessage ("400", "验证码不能为空！"));
            }
            if(obj==null || "".equals (obj)){
                throw  new BadCredentialsException(this.messages.getMessage ("400", "验证码失效，请重新发送！"));
            }
            if(!smsCode.equals (String.valueOf (obj))){
                throw  new BadCredentialsException(this.messages.getMessage ("400", "验证码不正确！"));
            }
            loadedUser = userDetailsService.loadUserByUsername (username);
        } catch (UsernameNotFoundException var6) {
            throw var6;
        } catch (Exception var7) {
            throw new InternalAuthenticationServiceException (var7.getMessage (), var7);
        }
        if (loadedUser == null) {
            throw new InternalAuthenticationServiceException ("UserDetailsService returned null, which is an interface contract violation");
        } else {
            return loadedUser;
        }
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return MobileSMSCodeAuthenticationToken.class.isAssignableFrom (authentication);
    }
}
