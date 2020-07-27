package com.fengwenyi.mp3demo.enums;

import org.springframework.http.HttpStatus;

/**
 * 所有业务异常父类
 *
 * @author walnut
 */
public abstract class ErrorCodeException extends RuntimeException {


    ErrorCodeException(String errorCode, ErrorCodeBean errorCodeBean) {
        this(errorCode, errorCodeBean.getHttpStatus(), errorCodeBean.getMessage());
    }
    ErrorCodeException(String errorCode, HttpStatus httpStatus, String message) {

        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    private String errorCode;
    private HttpStatus httpStatus;

}
