package com.fengwenyi.mp3demo.enums;

import org.springframework.http.HttpStatus;

public enum CommonErrorCodeEnum implements ErrorCodeAble{

    SYSTEM_ERROR("SYSTEM_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "系统异常"),
    VALIDATE_ERROR("INVALID_REQUEST", HttpStatus.BAD_REQUEST, "参数错误"),
    NOT_FOUND("NOT_FOUND", HttpStatus.NOT_FOUND, "没有找到对应资源")
    ;

    CommonErrorCodeEnum(String errorCode, HttpStatus httpStatus, String message) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    <T extends ErrorCodeException> CommonErrorCodeEnum(String errorCode, HttpStatus httpStatus, String message, Class<T> ex) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public ErrorCodeBean getErrorCodeBean() {
        ErrorCodeBean errorCodeBean = new ErrorCodeBean();
        errorCodeBean.setMessage(message);
        errorCodeBean.setErrorCode(errorCode);
        errorCodeBean.setHttpStatus(httpStatus);
        errorCodeBean.setCustomException(ex);

        return errorCodeBean;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }


    private String errorCode;
    private HttpStatus httpStatus;
    private String message;
    private Class<? extends ErrorCodeException> ex;
}
