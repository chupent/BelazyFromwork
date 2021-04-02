package com.belazy.basics.auth.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import java.util.Arrays;
import java.util.List;

/**
 * OAuth2异常转换类
 *
 * @author tangcp
 */
@Component
public class IOAuth2WebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer ();
    private List<Class> listException = Arrays.asList (OAuth2Exception.class, AuthenticationException.class, AccessDeniedException.class, HttpRequestMethodNotSupportedException.class);


    public ResponseEntity<OAuth2Exception> translate(Exception e) {
        Throwable[] causeChain = this.throwableAnalyzer.determineCauseChain (e);
        for (Class ec : listException) {
            Throwable throwable = this.throwableAnalyzer.getFirstThrowableOfType (ec, causeChain);
            if (null != throwable) {
                if (throwable instanceof OAuth2Exception) {//异常链中有OAuth2Exception异常
                    if(throwable instanceof InvalidGrantException){
                        return this.handleOAuth2Exception (new IOAuth2Exception (HttpStatus.UNAUTHORIZED.value (), "授权失败，账号或密码错误！", throwable));
                    }else {
                        return this.handleOAuth2Exception (new IOAuth2Exception (HttpStatus.UNAUTHORIZED.value (), "授权失败", throwable));
                    }
                }
                if (throwable instanceof AuthenticationException) {//身份验证相关异常
                    return this.handleOAuth2Exception (new IOAuth2Exception (HttpStatus.UNAUTHORIZED.value (), "授权失败，账号或密码错误！", throwable));
                }
                if (throwable instanceof AccessDeniedException) {//异常链中包含拒绝访问异常
                    return this.handleOAuth2Exception (new IOAuth2Exception (HttpStatus.FORBIDDEN.value (), "拒绝访问", throwable));
                }
                if (throwable instanceof HttpRequestMethodNotSupportedException) {//异常链中包含Http方法请求异常
                    return this.handleOAuth2Exception (new IOAuth2Exception (HttpStatus.METHOD_NOT_ALLOWED.value (), "HTTP请求方法异常", throwable));
                }
            }
        }
        return this.handleOAuth2Exception (new IOAuth2Exception (HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase (), e));
    }

    private ResponseEntity<OAuth2Exception> handleOAuth2Exception(OAuth2Exception e) {
        int status = e.getHttpErrorCode ();
        HttpHeaders headers = new HttpHeaders ();
        headers.set (HttpHeaders.CACHE_CONTROL, "no-store");
        headers.set (HttpHeaders.PRAGMA, "no-cache");
        if (status == HttpStatus.UNAUTHORIZED.value () || e instanceof InsufficientScopeException) {
            headers.set (HttpHeaders.WWW_AUTHENTICATE, String.format ("%s %s", "Bearer", e.getSummary ()));
        }
        ResponseEntity<OAuth2Exception> response = new ResponseEntity (e, headers, HttpStatus.valueOf (status));
        return response;
    }
}
