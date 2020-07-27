package com.fengwenyi.mp3demo.enums;


import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class ValidationException extends ErrorCodeException {

    ValidationException(ErrorCodeBean errorCodeBean) {
        super(errorCodeBean.getErrorCode(), errorCodeBean);
    }

    public ValidationException(String message) {
        super("CLIENT_ERROR", BAD_REQUEST, message);
    }
}
