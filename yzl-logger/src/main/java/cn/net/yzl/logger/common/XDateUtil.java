package cn.net.yzl.logger.common;

import org.springframework.util.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @title: XDateUtil
 * @Author lichanghong
 * @Date: 2020/11/30 9:39 上午
 * @Version 1.0
 */
public class XDateUtil {
    public static final DateTimeFormatter CTT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter CTT_DATE_TIME_WITH_MILLISECOND_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    public static final DateTimeFormatter CTT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final ZoneId CTT_ZONE_ID = ZoneId.of(ZoneId.SHORT_IDS.get("CTT"));

    public static Long createItemIdLong() {
        return System.nanoTime();
    }

    /**
     * 获取当前Instant时间
     * @return
     */
    public static Instant getCurrent(){
        return LocalDateTime.now()
                .atZone(CTT_ZONE_ID)
                .toInstant();
    }

    /**
     * 获取当前String时间(不带毫秒) ， 默认时间格式
     * @return
     */
    public static String getCurrentStringDate(){
        return toStringDate(getCurrent() , CTT_DATE_TIME_FORMATTER);
    }

    /**
     * 获取当前String时间， 指定时间格式
     * @return
     */
    public static String getCurrentStringDate(DateTimeFormatter dateTimeFormatter){
        return toStringDate(getCurrent() , dateTimeFormatter);
    }

    /**
     * 获取当前UTC String时间(0时区)，
     * 时间格式：isShowNano为true：yyyy-MM-ddTHH:mm:ss.SSSZ；isShowNano为false：yyyy-MM-ddTHH:mm:ssZ
     * @return
     */
    public static String getCurrentUtcStringSDate(Boolean isShowNano){
        long time = XDateUtil.getCurrentEpochMilli();
        int nanoOfSecond = isShowNano ? (int)(time % 1000) * 1000000 : 0;
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(time / 1000, nanoOfSecond, ZoneOffset.ofHours(0));
        return dateTime.toString()+"Z";
    }

    /**
     * 获取当前时间指定分钟后String时间
     * @return
     */
    public static String getCurrentPlusMinStringDate(Long mins){
        return toStringDate(
                LocalDateTime.now()
                        .plusMinutes(mins)
                        .atZone(CTT_ZONE_ID)
                        .toInstant()
                ,CTT_DATE_TIME_FORMATTER);
    }

    /**
     * 获取当前指定间隔后String时间
     * @return
     */
    public static String getCurrentPlusStringDate(int between, ChronoUnit chronoUnit){
        return toStringDate(
                LocalDateTime.now()
                        .plus(between , chronoUnit)
                        .atZone(CTT_ZONE_ID)
                        .toInstant()
                ,CTT_DATE_TIME_FORMATTER);
    }

    /**
     * 获取当前时间戳
     * @return
     */
    public static Long getCurrentEpochMilli(){
        return getCurrent().toEpochMilli();
    }

    /**
     * 指定时间转为时间戳
     * @param timeStr
     * @return
     */
    public static Long toEpochMilli(String timeStr){
        return toInstant(timeStr).toEpochMilli();
    }
    public static Long toEpochMilli(Instant time){
        return time.toEpochMilli();
    }


    /**
     * 根据时间戳格式化输出时间字符串，上海时区,默认DateTimeFormatter
     */
    public static String toStringDate(Long epochMilli) {
        return toStringDate(toInstant(epochMilli) , CTT_DATE_TIME_FORMATTER);
    }
    /**
     * 根据时间戳格式化输出时间字符串，上海时区,指定DateTimeFormatter
     */
    public static String toStringDate(Long epochMilli ,  DateTimeFormatter dateTimeFormatter) {
        return toStringDate(toInstant(epochMilli) , dateTimeFormatter);
    }
    /**
     * 格式化输出时间字符串(不带时间)，上海时区,默认DateTimeFormatter
     */
    public static String toStringDate(Instant instant) {
        return toStringDate(instant , CTT_DATE_FORMATTER);
    }
    /**
     * 格式化输出时间字符串(不带毫秒)，上海时区,默认DateTimeFormatter
     */
    public static String toStringDateTime(Instant instant) {
        return toStringDate(instant , CTT_DATE_TIME_FORMATTER);
    }
    /**
     * 格式化输出时间字符串，上海时区,指定DateTimeFormatter
     */
    public static String toStringDate(Instant instant , DateTimeFormatter dateTimeFormatter) {
        return LocalDateTime
                .ofInstant(instant, CTT_ZONE_ID)
                .format(dateTimeFormatter);
    }

    /**
     * UTC格式时间(0时区)转本地时间
     */
    public static String toStringDateByUtcDate(String utcDate , DateTimeFormatter dateTimeFormatter){
        ZonedDateTime zdt  = ZonedDateTime.parse(utcDate);
        LocalDateTime localDateTime = zdt.toLocalDateTime();
        return dateTimeFormatter.format(localDateTime.plusHours(8));
    }

    /**
     * 格式化输出时间，上海时区,默认DateTimeFormatter
     */
    public static Instant toInstant(String timeStr) {
        if(StringUtils.isEmpty(timeStr)) {
            return null;
        }

        return toInstant(timeStr , CTT_DATE_TIME_FORMATTER);
    }

    /**
     * 格式化输出时间，上海时区,指定DateTimeFormatter
     */
    public static Instant toInstant(String timeStr , DateTimeFormatter dateTimeFormatter) {
        return LocalDateTime
                .parse(timeStr, dateTimeFormatter)
                .atZone(CTT_ZONE_ID)
                .toInstant();
    }
    /**
     * 格式化输出时间，上海时区,默认DateTimeFormatter
     */
    public static Instant toInstant(Instant timeStr) {
        return LocalDateTime
                .ofInstant(timeStr, CTT_ZONE_ID)
                .atZone(CTT_ZONE_ID)
                .toInstant();
    }

    /**
     * 格式化输出时间，上海时区,默认DateTimeFormatter
     */
    public static Instant toInstant(Long epochMilli) {
        return Instant.
                ofEpochMilli(epochMilli).
                atZone(CTT_ZONE_ID).
                toInstant();
    }


    /**
     * 格式化输出时间，上海时区
     */
    public static String toStringDate(String timeStr) {
        return XDateUtil.toStringDate(XDateUtil.toInstant(timeStr));
    }

    /**
     * 获取指定年份
     */
    public static Integer getYear(String timeStr){
        return LocalDateTime
                .parse(timeStr, CTT_DATE_TIME_FORMATTER)
                .atZone(CTT_ZONE_ID)
                .getYear();
    }

    /**
     * 获取指定月份
     */
    public static Integer getMonth(String timeStr){
        return LocalDateTime
                .parse(timeStr, CTT_DATE_TIME_FORMATTER)
                .atZone(CTT_ZONE_ID)
                .getMonth()
                .getValue();
    }
}
