package com.fengwenyi.mp3demo.util;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @Description:
 * @author: zhengfei
 * @param:
 * @date: 2019-01-10 16:33
 */
public class DateUtil {

    public static final String DATE_PATTERN_ALL = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN_MIN = "yyyy-MM-dd HH:mm";
    public static final String DATE_PATTERN_YMD = "yyyy-MM-dd";
    public static final String DATE_PATTERN_PRINT = "MM月dd日 HH:mm";
    public static final String DATE_PATTERN_PRINT_MAINLAND = "yyyy.MM.dd HH:mm";

    /***
     * 获取两个相隔天数
     * @author zhengfei
     * @date 2019-01-10 16:34
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    /***
     *
     * @author zhengfei
     * @date 2019-01-14 15:02
     */
    public static Date getNextDay(Date date, int houre) {
        Date nextDay = DateUtils.addDays(date, 1);
        Calendar c = Calendar.getInstance();
        c.setTime(nextDay);
        c.set(Calendar.HOUR_OF_DAY, houre);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获取当前时间前n个小时
     *
     * @param hours
     * @return
     */
    public static Date beforHoursToNowDate(int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hours);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        Map<String, String> stringStringMap = Splitter.on("#").withKeyValueSeparator(":").split("125:0");
        System.err.println(stringStringMap);
    }
}
