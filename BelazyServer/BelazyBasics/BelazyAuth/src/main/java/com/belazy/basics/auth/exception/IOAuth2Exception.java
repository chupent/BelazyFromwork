package com.belazy.basics.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * OAuth2自定义异常
 *
 * @author tangcp
 */
@JsonSerialize(using = IOAuth2ExceptionSerializer.class)
public class IOAuth2Exception extends OAuth2Exception {

    public IOAuth2Exception(String msg, Throwable t) {
        super (msg, t);
    }

    public IOAuth2Exception(String msg) {
        super (msg);
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.BAD_REQUEST.value ();
    }
}
