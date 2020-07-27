package com.fengwenyi.mp3demo.exception;

/**
 * @author : Caixin
 * @date 2019/5/27 9:28
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -6413609500010314317L;
    private String errCode;
    private String errMsg;

    public BusinessException(String errMsg) {
        this.setErrMsg(errMsg);
    }

    public BusinessException(String errCode, String errMsg) {
        this.setErrCode(errCode);
        this.setErrMsg(errMsg);
    }

    public BusinessException(String errCode, String errMsg, Throwable cause) {
        super(cause);
        this.setErrCode(errCode);
        this.setErrMsg(errMsg);
    }

    public BusinessException(String errMsg, Throwable cause) {
        super(cause);
        this.setErrMsg(errMsg);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public String getErrCode() {
        return this.errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}

