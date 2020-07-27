package com.fengwenyi.mp3demo.response;

import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * @author : Caixin
 * @date 2019/5/27 9:34
 */
public final class R<T> extends AbstractResultMsg<T> {
    private R() {
        this.setHttpStatus(HttpStatus.OK.value());
        this.setErrorCode(ErrorCode.SUCCESS.getCode());
        this.setMessage(ErrorCode.SUCCESS.getPrompt());
    }

    private R(HttpStatus httpStatus, String message, String errorCode) {
        this.setHttpStatus(httpStatus.value());
        this.setMessage(message);
        this.setErrorCode(errorCode);
    }

    private R(T data) {
        this.setHttpStatus(HttpStatus.OK.value());
        this.setErrorCode(ErrorCode.SUCCESS.getCode());
        this.setMessage(ErrorCode.SUCCESS.getPrompt());
        this.setData(data);
    }

    public R(HttpStatus httpStatus, String message, String errorCode, T data) {
        this.setHttpStatus(httpStatus.value());
        this.setMessage(message);
        this.setErrorCode(errorCode);
        this.setData(data);
    }

    public static R success() {
        return new R();
    }

    public static R success(String message, String errorCode) {
        return new R(HttpStatus.OK, message, errorCode);
    }

    public static <T> R<T> success(T data) {
        return new R(data);
    }

    public static <T> R<List<T>> success(List<T> data) {
        return new R(data);
    }

    public static R error(HttpStatus httpStatus, String errorCode, String message) {
        return new R(httpStatus, message, errorCode);
    }
}
