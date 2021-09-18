package com.belazy.basics.auth.exception;

import com.belazy.basics.auth.enums.ErrorMessageEnum;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    public IOAuth2Exception(ErrorMessageEnum messageEnum,Throwable t){
        super (messageEnum.MESSAGE,t);
        this.httpErrorCode = messageEnum.CODE;
    }
    public IOAuth2Exception(ErrorMessageEnum messageEnum){
        super (messageEnum.MESSAGE);
        this.httpErrorCode = messageEnum.CODE;
    }

    @Override
    public int getHttpErrorCode() {
        if(httpErrorCode!=0){
            return httpErrorCode;
        }
        return super.getHttpErrorCode ();
    }
}
