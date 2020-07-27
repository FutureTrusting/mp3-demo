package com.fengwenyi.mp3demo.enums;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Constructor;
import java.util.Optional;


public interface ErrorCodeAble {

    static final Logger LOG = LoggerManager.REGULAR.getLogger(ErrorCodeAble.class);

    ErrorCodeBean getErrorCodeBean();

    default ErrorCodeException createException(String message) {
        ErrorCodeBean errorCodeBean = getErrorCodeBean();
        errorCodeBean.setMessage(message);
        return ExceptionUtils.createException(errorCodeBean);
    }

    default ErrorCodeException createException() {
        return ExceptionUtils.createException(getErrorCodeBean());
    }

    default ErrorCodeException createException(Throwable cause) {
        return ExceptionUtils.createException(getErrorCodeBean(), cause);
    }

    default ErrorCodeException createException(String message, Throwable cause) {
        ErrorCodeBean errorCodeBean = getErrorCodeBean();
        errorCodeBean.setMessage(message);
        return ExceptionUtils.createException(getErrorCodeBean(), cause);
    }

    class ExceptionUtils {

        static ErrorCodeException createException(ErrorCodeBean errorCodeBean) {
            HttpStatus httpStatus = errorCodeBean.getHttpStatus();

            Optional<Class<? extends ErrorCodeException>> customExceptionOption = errorCodeBean.getCustomExceptionOption();
            if(customExceptionOption.isPresent()) {
                Class<? extends ErrorCodeException> customClass = customExceptionOption.get();
                if(ValidationException.class.isAssignableFrom(customClass)
                        && !errorCodeBean.getHttpStatus().is4xxClientError()) {
                    LOG.warn(customClass+"不是ValidationException子类，HttpStatus为4xx");
                }
                if(ServiceException.class.isAssignableFrom(customClass)
                        && !errorCodeBean.getHttpStatus().is5xxServerError()) {
                    LOG.warn(customClass+"不是ServiceException子类，HttpStatus为5xx");
                }

                return createCustomException(errorCodeBean, customClass);
            }
            if(httpStatus.is4xxClientError()){
                return new ValidationException(errorCodeBean);
            }
            if(httpStatus.is5xxServerError()){
                return new ServiceException( errorCodeBean);
            }
            return new ServiceException(errorCodeBean);
        }

        static ErrorCodeException createException(ErrorCodeBean errorCodeBean, Throwable cause) {
            ErrorCodeException exception = createException(errorCodeBean);
            exception.initCause(cause);
            return exception;
        }

        private static ErrorCodeException createCustomException(ErrorCodeBean errorCodeBean, final Class customerEx) {

            if(ErrorCodeException.class.isAssignableFrom(customerEx)){
                try {
                    Constructor constructor = customerEx.getDeclaredConstructor(ErrorCodeBean.class);
                    return (ErrorCodeException) constructor.newInstance(errorCodeBean);
                } catch (ReflectiveOperationException e) {
                    ErrorCodeException sysEx = CommonErrorCodeEnum.SYSTEM_ERROR.createException();
                    sysEx.initCause(e);
                    LOG.error(customerEx + "中未定义带有ErrorCodeBean参数的public构造函数", sysEx);
                    throw sysEx;
                }
            }

            ErrorCodeException sysEx = CommonErrorCodeEnum.SYSTEM_ERROR.createException();
            LOG.error(customerEx + "不是ErrorCodeException的子类", sysEx);
            throw sysEx;
        }

    }


}
