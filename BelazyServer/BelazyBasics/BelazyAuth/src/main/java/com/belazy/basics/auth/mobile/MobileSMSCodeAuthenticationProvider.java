package com.belazy.basics.auth.mobile;

import com.belazy.basics.auth.enums.ErrorMessageEnum;
import com.belazy.basics.auth.exception.IOAuth2Exception;
import com.belazy.basics.auth.service.IUserDetailService;
import com.belazy.library.constant.RedisConstant;
import com.belazy.library.redis.service.RedisService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

/**
 * 手机号+验证码验证提供者
 *
 * @author tangcp
 */
@Slf4j
public class MobileSMSCodeAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Setter
    private IUserDetailService iUserDetailService;
    @Setter
    private RedisService redisService;

    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        MobileSMSCodeAuthenticationToken token = (MobileSMSCodeAuthenticationToken) authentication;
        MobileSMSCode param = token.getParam ();
        //校验验证码是否正确
        String smsCode =param.getSmsCode ();
        Object obj = redisService.get (RedisConstant.LOGIN_SMS_CODE_KEY + param.getUsername ());
        if (obj == null || "".equals (obj)) {
            log.error ("additionalAuthenticationChecks ==> 验证码失效，请重新发送！");
            throw new IOAuth2Exception (ErrorMessageEnum.SMS_CODE_EXPIRE);
        }
        if (!smsCode.equals (String.valueOf (obj))) {
            log.error ("additionalAuthenticationChecks ==> 验证码不正确！");
            throw new IOAuth2Exception (ErrorMessageEnum.SMS_CODE_ERROR);
        }
        redisService.del (RedisConstant.LOGIN_SMS_CODE_KEY + param.getUsername ());//校验完毕清楚验证码记录
    }

    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        UserDetails loadedUser;
        try {
            MobileSMSCodeAuthenticationToken token = (MobileSMSCodeAuthenticationToken) usernamePasswordAuthenticationToken;
            MobileSMSCode param = token.getParam ();
            if (param == null) {
                log.error ("retrieveUser ==> MobileSMSCodeParam is null!");
                throw new IOAuth2Exception (ErrorMessageEnum.PARAMS_ERROR);
            }
            if (StringUtils.isEmpty (param.getSmsCode ())) {
                log.error ("retrieveUser ==> sms code is null!");
                throw new IOAuth2Exception (ErrorMessageEnum.SMS_CODE_ERROR);
            }
            loadedUser = iUserDetailService.loadUserByMobile (param.getUsername ());
        } catch (UsernameNotFoundException var6) {
            log.error ("retrieveUser ==> UsernameNotFoundException");
            throw var6;
        } catch (Exception var7) {
            log.error ("retrieveUser ==> InternalAuthenticationServiceException");
            throw new InternalAuthenticationServiceException (var7.getMessage (), var7);
        }
        if (loadedUser == null) {
            log.error ("retrieveUser ==> loadedUser is null");
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
