package com.fengwenyi.mp3demo.exception;

import com.fengwenyi.mp3demo.response.ErrorStatusEnum;
import com.fengwenyi.mp3demo.response.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author : Caixin
 * @date 2019/5/27 9:30
 */
@ControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ResponseBody
    @ExceptionHandler({ConstraintViolationException.class})
    public Object constraintViolationException(ConstraintViolationException exception) {
        List<String> invalidArguments = new ArrayList<>();
        Set<ConstraintViolation<?>> errors = exception.getConstraintViolations();
        for (ConstraintViolation<?> error : errors) {
            String field = ((PathImpl) error.getPropertyPath()).getLeafNode().toString();
            invalidArguments.add(field + ":" + error.getMessage());
        }

        String errorStr = StringUtils.join(invalidArguments, ",");
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatusEnum.INVALID_ARGUMENTS.getValue(), errorStr);
    }

    @ResponseBody
    @ExceptionHandler({BindException.class})
    public Object methodArgumentNotValidHandler(BindException exception) {
        String errorStr = exception.getBindingResult().getAllErrors().stream().findFirst().map(DefaultMessageSourceResolvable::getDefaultMessage).orElse(null);
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatusEnum.INVALID_ARGUMENTS.getValue(), errorStr);
    }

    @ResponseBody
    @ExceptionHandler({ServletRequestBindingException.class, MethodArgumentTypeMismatchException.class})
    public Object servletArgumentNotValidHandler(ServletRequestBindingException exception) {
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatusEnum.INVALID_ARGUMENTS.getValue(), exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Object httpRequestMethodNotValidHandler(HttpRequestMethodNotSupportedException exception) {
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatusEnum.FORBIDDEN_REQUEST.getValue(), exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({BusinessException.class})
    public Object businessExceptionHandler(BusinessException exception) {
        Throwable throwable = this.findRootExceptionThrowable(exception);
        if (throwable != null && !(throwable instanceof BusinessException)) {
            log.error("业务异常: {}, {}", exception.getErrMsg(), exception);
        } else {
            log.warn("业务异常: {}, {}", exception.getErrMsg(), exception.getStackTrace().length > 0 ? exception.getStackTrace()[0] : "");
        }

        return R.error(HttpStatus.INTERNAL_SERVER_ERROR, StringUtils.isBlank(exception.getErrCode()) ? ErrorStatusEnum.BUSINESS_ERROR.getValue() : exception.getErrCode(), exception.getErrMsg());
    }

    private Throwable findRootExceptionThrowable(Exception e) {
        Throwable rootCause;
        rootCause = e;
        while (rootCause.getCause() != null) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }

    @ResponseBody
    @ExceptionHandler({MyBatisSystemException.class})
    public Object myBatisSystemExceptionHandler(MyBatisSystemException exception) {
        log.error("mybatis异常", exception);
        return this.gerUnkownExceptionMessage(exception);
    }

    @ResponseBody
    @ExceptionHandler({NullPointerException.class})
    public Object myBatisSystemExceptionHandler(NullPointerException exception) {
        log.error("空指针异常！", exception);
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatusEnum.MISSING_SESSION.getValue(), exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({DuplicateKeyException.class})
    public Object dataIntegrityViolationException(DuplicateKeyException exception) {
        log.error("数据库异常", exception);
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatusEnum.INVALID_ARGUMENTS.getValue(), "主键冲突或违反唯一约束");
    }

    @ResponseBody
    @ExceptionHandler({DataIntegrityViolationException.class})
    public Object dataIntegrityViolationException(DataIntegrityViolationException exception) {
        log.error("数据库异常", exception);
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatusEnum.INVALID_ARGUMENTS.getValue(), "内容超长或者数据异常");
    }

    @ResponseBody
    @ExceptionHandler({Exception.class})
    public Object unknownExceptionHandler(Exception exception) {
        log.error("系统异常", exception);
        return this.gerUnkownExceptionMessage(exception);
    }

    @ResponseBody
    @ExceptionHandler({NumberFormatException.class})
    public Object unknownExceptionHandler(NumberFormatException exception) {
        log.error("输入格式异常！", exception);
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatusEnum.INVALID_ARGUMENTS.getValue(), "请输入数字格式的数据！");
    }

//    @ExceptionHandler(value = KaptchaException.class)
//    @ResponseBody
//    public R kaptchaExceptionHandler(KaptchaException kaptchaException) {
//        if (kaptchaException instanceof KaptchaIncorrectException) {
//            return R.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodeEnum.INCORRECT_VERIFICATION_CODE.getCode(), ErrorCodeEnum.INCORRECT_VERIFICATION_CODE.getPrompt());
//        } else if (kaptchaException instanceof KaptchaNotFoundException) {
//            return R.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodeEnum.INCORRECT_VERIFICATION_CODE.getCode(), ErrorCodeEnum.INCORRECT_VERIFICATION_CODE.getPrompt());
//        } else if (kaptchaException instanceof KaptchaTimeoutException) {
//            return R.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodeEnum.VERIFICATION_CODE_EXPIRED.getCode(), ErrorCodeEnum.VERIFICATION_CODE_EXPIRED.getPrompt());
//        } else {
//            return R.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodeEnum.CAPTCHA_RENDERING_FAILED.getCode(), ErrorCodeEnum.CAPTCHA_RENDERING_FAILED.getPrompt());
//        }
//    }

//    @ResponseBody
//    @ExceptionHandler({UnauthorizedException.class})
//    public Object authorizationException() {
//        return R.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatusEnum.INSUFFICIENT_USER_PERMISSIONS.getValue(), ErrorStatusEnum.INSUFFICIENT_USER_PERMISSIONS.getText());
//    }

    private R gerUnkownExceptionMessage(Exception exception) {
        String message = exception.getMessage();
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatusEnum.UNKNOWN_ERRROR.getValue(), message);
    }
}