package com.xyz.im.base.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class DateUtils {

    /**
     * 按指定时分秒获取带明天日期的日期时间
     *
     * @param time HH:mm:ss
     * @return
     */
    public static Date getTomSpecifiedTime(String time) {
        time = getTomorrow() + " " + time;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            log.error("日期转换异常", e);
        }

        return date;
    }

    /**
     * 获取明天日期的字符串
     *
     * @return
     */
    public static String getTomorrow() {
        return LocalDate.now().minusDays(-1).toString();
    }

    /**
     * 根据传入时间，计算和现在比较还有多少毫秒，传入时间必须大于当前时间
     *
     * @param date
     * @return
     */
    public static long getSpecifiedMS(Date date) {
        Date now = new Date();
        if (!date.after(now)) {
            try {
                throw new Exception("传入时间必须大于当前时间");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        long ms = date.getTime() - now.getTime();
        return ms;
    }

    /**
     * 获取当月的天数
     *
     * @return
     */
    public static int getCurrentMonthDays() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当年的天数
     *
     * @return
     */
    public static int getCurrentYearDays() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取下个月的同一日期
     *
     * @param time
     * @return
     */
    public static long getNextMonthDate(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取下一年的同一日期
     *
     * @param time
     * @return
     */
    public static long getNextYearDate(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        calendar.add(Calendar.YEAR, 1);
        return calendar.getTimeInMillis();
    }

    /**
     * 返回格式 20180606
     *
     * @param offset 偏移量 0当天 -1昨天 1明天 以此类推
     * @return
     */
    public static int date(int offset) {
        String result = new DateTime().plusDays(offset).toString("yyyyMMdd");
        return NumberUtils.toInt(result);
    }

    /**
     * 获取当周周一
     *
     * @param format
     * @return
     */
    public static String getThisMonday(String format) {
        DateTime now = DateTime.now();
        DateTime monday = now.withDayOfWeek(DateTimeConstants.MONDAY);
        return monday.toString(format);
    }

    /**
     * 获取当周周日
     *
     * @param format
     * @return
     */
    public static String getThisSunday(String format) {
        DateTime now = DateTime.now();
        DateTime monday = now.withDayOfWeek(DateTimeConstants.SUNDAY);
        return monday.toString(format);
    }

}
