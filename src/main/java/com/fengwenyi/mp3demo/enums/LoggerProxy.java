package com.fengwenyi.mp3demo.enums;

import org.slf4j.Logger;
import org.slf4j.Marker;

/**
 * 代理slf4j的Logger类，使用自己定义的Marker
 *
 * @author walnut
 */
class LoggerProxy implements Logger {

    public LoggerProxy(Logger logger, Marker marker) {
        this.logger = logger;
        this.marker = marker;
    }

    @Override
    public String getName() {
        return logger.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
        logger.trace(marker, msg);
    }

    @Override
    public void trace(String format, Object arg) {
        logger.trace(marker, format, arg);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        logger.trace(marker, format, arg1, arg2);
    }

    @Override
    public void trace(String format, Object... arguments) {
        logger.trace(marker, format, arguments);
    }

    @Override
    public void trace(String msg, Throwable t) {
        logger.trace(marker, msg, t);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void trace(Marker marker, String msg) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void trace(Marker marker, String format, Object... argArray) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        logger.debug(marker, msg);
    }

    @Override
    public void debug(String format, Object arg) {
        logger.debug(marker, format, arg);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        logger.debug(marker, format, arg1, arg2);
    }

    @Override
    public void debug(String format, Object... arguments) {
        logger.debug(marker, format, arguments);
    }

    @Override
    public void debug(String msg, Throwable t) {
        logger.debug(marker, msg, t);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void debug(Marker marker, String msg) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void debug(Marker marker, String format, Object... arguments) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public void info(String msg) {
        logger.info(marker, msg);
    }

    @Override
    public void info(String format, Object arg) {
        logger.info(marker, format, arg);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        logger.info(marker, format, arg1, arg2);
    }

    @Override
    public void info(String format, Object... arguments) {
        logger.info(marker, format, arguments);
    }

    @Override
    public void info(String msg, Throwable t) {
        logger.info(marker, msg, t);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void info(Marker marker, String msg) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void info(Marker marker, String format, Object... arguments) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void info(Marker marker, String msg, Throwable t) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    public void warn(String msg) {
        logger.warn(marker, msg);
    }

    @Override
    public void warn(String format, Object arg) {
        logger.warn(marker, format, arg);
    }

    @Override
    public void warn(String format, Object... arguments) {
        logger.warn(marker, format, arguments);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        logger.warn(marker, format, arg1, arg2);
    }

    @Override
    public void warn(String msg, Throwable t) {
        logger.warn(marker, msg, t);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void warn(Marker marker, String msg) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void warn(Marker marker, String format, Object... arguments) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override
    public void error(String msg) {
        logger.error(marker, msg);
    }

    @Override
    public void error(String format, Object arg) {
        logger.error(marker, format, arg);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        logger.error(marker, format, arg1, arg2);
    }

    @Override
    public void error(String format, Object... arguments) {
        logger.error(marker, format, arguments);
    }

    @Override
    public void error(String msg, Throwable t) {
        logger.error(marker, msg, t);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void error(Marker marker, String msg) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    @Override
    public void error(Marker marker, String format, Object... arguments) {
        logger.error(marker, format, arguments);
    }

    @Override
    public void error(Marker marker, String msg, Throwable t) {
        throw new UnsupportedOperationException("不支持自定义Marker");
    }

    private Logger logger;
    private Marker marker;

}
