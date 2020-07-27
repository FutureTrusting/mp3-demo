package com.fengwenyi.mp3demo.enums;

import org.springframework.http.HttpStatus;

/**
 * 服务异常，业务代码常用异常
 *
 * @author walnut
 */
public class ServiceException extends ErrorCodeException {

    /**
     * 通过 ErrorCodeAble 创建异常类
     *
     * @param errorCodeAble 可以定义enum实现ErrorCodeAble
     */
    @Deprecated
    public static ServiceException of(ErrorCodeAble errorCodeAble) {
        errorCodeAble.getErrorCodeBean().setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ServiceException(errorCodeAble.getErrorCodeBean());
    }

    /**
     * 通过 ErrorCodeAble 创建异常类
     *
     * @param errorCodeAble 可以定义enum实现ErrorCodeAble
     * @param message 可以覆盖配置中的默认消息
     */
    @Deprecated
    public static ServiceException of(ErrorCodeAble errorCodeAble, String message) {
        ErrorCodeBean errorCodeBean = errorCodeAble.getErrorCodeBean();
        errorCodeBean.setMessage(message);
        errorCodeBean.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ServiceException(errorCodeBean);
    }

    ServiceException(ErrorCodeBean errorCodeBean) {
        super(errorCodeBean.getErrorCode(), errorCodeBean);
    }

}
