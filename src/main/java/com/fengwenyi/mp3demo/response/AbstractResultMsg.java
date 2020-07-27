package com.fengwenyi.mp3demo.response;

import java.io.Serializable;

/**
 * @author : Caixin
 * @date 2019/5/27 9:34
 */
public abstract class AbstractResultMsg<T> implements Serializable {
    protected String errorCode;
    protected String message;
    protected int httpStatus;
    protected T data;

    public AbstractResultMsg() {
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    public int getHttpStatus() {
        return this.httpStatus;
    }

    public T getData() {
        return this.data;
    }

    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setHttpStatus(final int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setData(final T data) {
        this.data = data;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof AbstractResultMsg)) {
            return false;
        } else {
            AbstractResultMsg<?> other = (AbstractResultMsg)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$errorCode = this.getErrorCode();
                Object other$errorCode = other.getErrorCode();
                if (this$errorCode == null) {
                    if (other$errorCode != null) {
                        return false;
                    }
                } else if (!this$errorCode.equals(other$errorCode)) {
                    return false;
                }

                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if (this$message == null) {
                    if (other$message != null) {
                        return false;
                    }
                } else if (!this$message.equals(other$message)) {
                    return false;
                }

                if (this.getHttpStatus() != other.getHttpStatus()) {
                    return false;
                } else {
                    Object this$data = this.getData();
                    Object other$data = other.getData();
                    if (this$data == null) {
                        if (other$data != null) {
                            return false;
                        }
                    } else if (!this$data.equals(other$data)) {
                        return false;
                    }

                    return true;
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AbstractResultMsg;
    }

    @Override
    public int hashCode() {
        int result = 1;
        Object errorCode = this.getErrorCode();
        result = 59 * result + (errorCode == null ? 43 : errorCode.hashCode());
        Object message = this.getMessage();
        result = result * 59 + (message == null ? 43 : message.hashCode());
        result = result * 59 + this.getHttpStatus();
        Object data = this.getData();
        result = result * 59 + (data == null ? 43 : data.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "AbstractResultMsg(errorCode=" + this.getErrorCode() + ", message=" + this.getMessage() + ", httpStatus=" + this.getHttpStatus() + ", data=" + this.getData() + ")";
    }
}

