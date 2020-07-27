package com.fengwenyi.mp3demo.enums;


import org.springframework.http.HttpStatus;

public enum ErrorCodeEnum implements ErrorCodeAble {

    COMMON_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_ERROR","通用错误!"),
    RESOURCE_FORBIDDEN(HttpStatus.FORBIDDEN, "RESOURCE_FORBIDDON","禁止访问!"),
    QUERY_SOURCE_ERROR(HttpStatus.BAD_REQUEST, "QUERY_SOURCE_ERROR","源数据不存在"),

    PARAM_ERROR(HttpStatus.BAD_REQUEST, "PARAM_ERROR","参数验证错误"),

    QUERY_PARENT_ERROR(HttpStatus.BAD_REQUEST, "QUERY_PARENT_ERROR","父节点不存在"),
    QUERY_EXIST_ERROR(HttpStatus.BAD_REQUEST, "QUERY_EXIST_ERROR","数据已存在"),
    APP_NOT_EXIST_ERROR(HttpStatus.BAD_REQUEST, "APP_NOT_EXIST_ERROR","该系统不存在"),
    APP_AREA_ERROR(HttpStatus.BAD_REQUEST, "APP_AREA_ERROR","该地区不存在"),
    APP_APPCODE_ERROR(HttpStatus.BAD_REQUEST, "APP_APPCODE_ERROR","所属系统编码错误"),
    PARAM_EMPTY_ERROR(HttpStatus.BAD_REQUEST, "PARAM_EMPTY_ERROR","参数不能为空"),
    ACCESS_DENY_ERROR(HttpStatus.UNAUTHORIZED, "ACCESS_DENY_ERROR","无权限操作"),
    APPCODE_EMPTY_ERROR(HttpStatus.BAD_REQUEST, "APPCODE_EMPTY_ERROR","系统编码不能为空"),
    COMMON_USERNAME_ERROR(HttpStatus.BAD_REQUEST, "COMMON_USERNAME_ERROR","普通用户姓名必须包含非数字!"),


    //menu模块
    MENU_METHOD_ERROR(HttpStatus.BAD_REQUEST, "MENU_METHOD_ERROR","权限：method参数值错误"),
    MENU_TYPE_ERROR(HttpStatus.BAD_REQUEST, "MENU_TYPE_ERROR","权限：类型（type）错误"),

    // 用户模块
    USER_ENUM_ERROR(HttpStatus.BAD_REQUEST, "USER_ENUM_ERROR","用户状态不正确!"),
    USER_UNAME_PWD_ERROR(HttpStatus.BAD_REQUEST, "USER_UNAME_PWD_ERROR","用户名或密码输入错误!"),
    USER_TYPE_ERROR(HttpStatus.BAD_REQUEST, "USER_TYPE_ERROR","用户类型错误!"),
    USER_EMAIL_ERROR(HttpStatus.BAD_REQUEST, "USER_EMAIL_ERROR","邮箱格式错误!"),
    USER_PHONE_ERROR(HttpStatus.BAD_REQUEST, "USER_PHONE_ERROR","电话号码格式错误!"),
    USER_NOT_FIRST_LOGIN(HttpStatus.BAD_REQUEST, "USER_NOT_FIRST_LOGIN","用户不是第一次登录!"),

    //角色模块
    ROLE_TYPE_ERROR(HttpStatus.BAD_REQUEST, "ROLE_TYPE_ERROR","角色类型错误"),
    ROLE_DELETE_ERROR(HttpStatus.BAD_REQUEST, "ROLE_DELETE_ERROR","无权限操作其中的角色"),
    ROLE_NOT_BELONG_ERROR(HttpStatus.BAD_REQUEST, "ROLE_NOT_BELONG_ERROR","角色所属错误"),


    //组织模块
    ORG_NAME_ERROR(HttpStatus.BAD_REQUEST, "ORG_NAME_ERROR","组织名称格式错误"),
    ORG_ORDER_NUM_ERROR(HttpStatus.BAD_REQUEST, "ORG_NAME_ERROR","组织排序序号错误"),
    ORG_ORG_CODE_ERROR(HttpStatus.BAD_REQUEST, "ORG_NAME_ERROR","组织代码错误"),
    ORG_ROOT_NODE_ERROR(HttpStatus.BAD_REQUEST, "ORG_ROOT_NODE_ERROR","当前用户的根节点不可删除"),
    ORG_ROOT_NODE_UPDATE_ERROR(HttpStatus.BAD_REQUEST, "ORG_ROOT_NODE_ERROR","当前用户的根节点不可修改"),
    ORG_NOT_BELONG_ERROR(HttpStatus.BAD_REQUEST, "ORG_NOT_BELONG_ERROR","该组织不在该用户所辖内"),
    ORG_NO_PERMISSION_ERROR(HttpStatus.BAD_REQUEST, "ORG_NOT_BELONG_ERROR","无权限操作该组织"),
    ORG_PARENT_ID_ERROR(HttpStatus.BAD_REQUEST, "ORG_PARENT_ID_ERROR","当前组织父节点ID错误"),
    ORG_NOT_EXIST(HttpStatus.BAD_REQUEST, "ORG_NOT_EXIST","未找到用户所属组织"),
    ORG_IMPORT_LEVEL_LIMIT(HttpStatus.BAD_REQUEST, "ORG_IMPORT_LEVEL_LIMIT","无权限导入组织"),


    //URL 模块
    URL_ATTRIBUTE_NOT_EMPTY(HttpStatus.BAD_REQUEST, "URL_NAME_ERROR","URL属性为空")
    ;


    private HttpStatus status;
    private String errorCode;
    private String errorMessage;

    private ErrorCodeEnum(HttpStatus status, String errorCode, String errorMessage) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }


    @Override
    public ErrorCodeBean getErrorCodeBean() {
        ErrorCodeBean bean = new ErrorCodeBean();
        bean.setHttpStatus(this.status);
        bean.setMessage(this.errorMessage);
        bean.setErrorCode(this.errorCode);
        return bean;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}
