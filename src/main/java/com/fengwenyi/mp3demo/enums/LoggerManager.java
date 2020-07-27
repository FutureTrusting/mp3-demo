package com.fengwenyi.mp3demo.enums;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * 全局日志类
 *
 * @author walnut
 */
public enum LoggerManager {

    /** 常规日志*/
    REGULAR("REGULAR"),

    /** 逃逸的异常*/
    ESCAPE_EXCEPTION("ESCAPE_EXCEPTION"),

    /** 访问日志*/
    ACC("ACC");

    LoggerManager(String markerName) {
        this.marker = MarkerFactory.getMarker(markerName);

    }

    private Marker marker;


    public Logger getLogger(Class<?> clazz) {
        return new LoggerProxy(org.slf4j.LoggerFactory.getLogger(clazz), marker);
    }
}