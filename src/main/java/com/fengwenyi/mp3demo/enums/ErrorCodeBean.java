package com.fengwenyi.mp3demo.enums;

import org.springframework.http.HttpStatus;

import java.util.Optional;

/**
 * 用来接受apollo中的errorCode的对象
 *
 * @author walnut
 */
public class ErrorCodeBean {


    private String errorCode;

    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    private String message;

    private Optional<Class<? extends ErrorCodeException>> customExceptionOption = Optional.empty();


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Optional<Class<? extends ErrorCodeException>> getCustomExceptionOption() {
        return customExceptionOption;
    }

    public void setCustomException(Class<? extends ErrorCodeException> customException) {
        this.customExceptionOption = Optional.ofNullable(customException);
    }

    public static ErrorCodeBean DEFAULT_BEAN = new ErrorCodeBean();


    static {
        DEFAULT_BEAN.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        DEFAULT_BEAN.message = "系统错误";
    }
}
