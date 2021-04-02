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
    private int httpErrorCode;
    public IOAuth2Exception(int httpErrorCode,String msg, Throwable t) {
        super (msg, t);
        this.httpErrorCode = httpErrorCode;
    }

    public IOAuth2Exception(String msg, Throwable t) {
        super (msg, t);
    }

    public IOAuth2Exception(String msg) {
        super (msg);
    }

    @Override
    public int getHttpErrorCode() {
        if(httpErrorCode!=0){
            return httpErrorCode;
        }
        return super.getHttpErrorCode ();
    }
}
