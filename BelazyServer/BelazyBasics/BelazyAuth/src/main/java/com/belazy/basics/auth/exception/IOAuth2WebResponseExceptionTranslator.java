package com.belazy.basics.auth.exception;

import com.belazy.basics.auth.enums.ErrorMessageEnum;
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
                        return this.handleOAuth2Exception (new IOAuth2Exception (ErrorMessageEnum.INVALID_GRANT, throwable));
                    } else if(throwable instanceof IOAuth2Exception){
                        return this.handleOAuth2Exception ((IOAuth2Exception) throwable);
                    } else {
                        return this.handleOAuth2Exception (new IOAuth2Exception (ErrorMessageEnum.AUTH_EXCEPTION, throwable));
                    }
                }
                if (throwable instanceof AuthenticationException) {//身份验证相关异常
                    return this.handleOAuth2Exception (new IOAuth2Exception (ErrorMessageEnum.INVALID_GRANT, throwable));
                }
                if (throwable instanceof AccessDeniedException) {//异常链中包含拒绝访问异常
                    return this.handleOAuth2Exception (new IOAuth2Exception (ErrorMessageEnum.ACCESS_DENIED, throwable));
                }
                if (throwable instanceof HttpRequestMethodNotSupportedException) {//异常链中包含Http方法请求异常
                    return this.handleOAuth2Exception (new IOAuth2Exception (ErrorMessageEnum.HTTP_REQUEST_METHOD_NOT_SUPPORTED, throwable));
                }
            }
        }
        return this.handleOAuth2Exception (new IOAuth2Exception (ErrorMessageEnum.INTERNAL_SERVER_ERROR, e));
    }

    private ResponseEntity<OAuth2Exception> handleOAuth2Exception(OAuth2Exception e) {
        int status = e.getHttpErrorCode ();
        HttpHeaders headers = new HttpHeaders ();
        ResponseEntity<OAuth2Exception> response = new ResponseEntity (e, headers, HttpStatus.valueOf (status));
        return response;
    }
}
