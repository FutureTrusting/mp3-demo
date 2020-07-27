package com.fengwenyi.mp3demo.exception;

/**
 * @author : Caixin
 * @date 2019/5/27 9:29
 */
public class InvalidParameterException extends BusinessException {
    public InvalidParameterException(String errMsg) {
        super(errMsg);
    }
}
