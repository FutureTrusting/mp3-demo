package com.fengwenyi.mp3demo.enums;

import org.springframework.http.HttpStatus;

/***
 * BspErrorCodeEnum
 * @author zhengfei
 * @date 2019-06-18 12:43
 */
public enum BspErrorCodeEnum implements ErrorCodeAble {
    /**客户编码为空*/
    ACCESSCODE_EMPTY("ACCESSCODE_EMPTY", HttpStatus.BAD_REQUEST, "客户编码为空"),
    /**校验码为空*/
    CHECKWORD_EMPTY("CHECKWORD_EMPTY", HttpStatus.BAD_REQUEST, "校验码为空"),
    /**bsp请求地址获取失败*/
    BSP_REQUEST_URL_EMPTY("BSP_REQUEST_URL_EMPTY", HttpStatus.INTERNAL_SERVER_ERROR, "bsp请求地址获取失败"),
    /**业务模板不存在*/
    NOT_EXIST_TEMPLATE("NOT_EXIST_TEMPLATE", HttpStatus.BAD_REQUEST, "业务模板不存在"),
    /**请求入参校验不通过*/
    PARAMS_CHECK_ERROR("PARAMS_CHECK_ERROR", HttpStatus.BAD_REQUEST, "请求入参校验不通过"),
    /**bsp无法连接导致请求失败*/
    BSP_CONNECT_TIMEOUT("BSP_CONNECT_TIMEOUT", HttpStatus.INTERNAL_SERVER_ERROR, "bsp无法连接导致请求失败"),
    /**bsp操作返回错误*/
    BSP_OPERATION_FAILED("BSP_OPERATION_FAILED", HttpStatus.INTERNAL_SERVER_ERROR, "bsp操作返回错误"),
    /**请求入参对象转换失败*/
    REQUEST_OBJECT_TRANSFER_ERROR("REQUEST_OBJECT_TRANSFER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "请求入参对象转换失败"),
    /**返回出参对象转换失败*/
    RESPONSE_OBJECT_TRANSFER_ERROR("RESPONSE_OBJECT_TRANSFER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "返回出参对象转换失败");

    BspErrorCodeEnum(String errorCode, HttpStatus httpStatus, String message) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    private String errorCode;

    private HttpStatus httpStatus;

    private String message;

    private Class<? extends ErrorCodeException> ex;

    @Override
    public ErrorCodeBean getErrorCodeBean() {
        ErrorCodeBean errorCodeBean = new ErrorCodeBean();
        errorCodeBean.setMessage(this.message);
        errorCodeBean.setErrorCode(this.errorCode);
        errorCodeBean.setHttpStatus(this.httpStatus);
        errorCodeBean.setCustomException(ex);
        return errorCodeBean;
    }
}
