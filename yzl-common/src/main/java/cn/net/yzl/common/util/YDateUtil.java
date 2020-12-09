package cn.net.yzl.common.util;

import org.springframework.util.StringUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 *
 **/

public class YDateUtil {
    public static final DateTimeFormatter CTT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter CTT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter CTT_DATE_HOUR_MIN_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter CTT_DATE_ISO_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    public static final ZoneId CTT_ZONE_ID = ZoneId.of(ZoneId.SHORT_IDS.get("CTT"));
    private static final ThreadLocal<DateFormat> commonFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    public static final String FORMAT_STR = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前Instant时间
     *
     * @return
     */
    public static Instant getCurrent() {
        return LocalDateTime.now()
                .atZone(CTT_ZONE_ID)
                .toInstant();
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static String getCurrentYear() {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return year;
    }

    /**
     * 获取秒级时间戳 String时间
     *
     * @return
     */
    public static String getStringDateFromEpochMilli(Long time) {
        Instant instant = Instant.ofEpochMilli(time * 1000);
        return toStringDate(instant, CTT_DATE_TIME_FORMATTER);
    }

    /**
     * 获取时分
     *
     * @return
     */
    public static String getStringDateHourMin(String time) {
        Instant instant = Instant.ofEpochMilli(toEpochMilli(time) * 1000);
        return toStringDate(instant, CTT_DATE_HOUR_MIN_FORMATTER);
    }

    /**
     * 获取星期几
     *
     * @param time
     * @return
     */
    public static String getDayOfWeek(Long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time * 1000);
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week <= 0) {
            week = 7;
        }
        return String.valueOf(week);
    }


    /**
     * 获取当前String时间
     *
     * @return
     */
    public static String getCurrentStringDate() {
        return toStringDate(getCurrent(), CTT_DATE_TIME_FORMATTER);
    }

    /**
     * 获取指定分钟后String时间
     *
     * @return
     */
    public static String getPlusMinStringDate(Integer mins) {
        return toStringDate(
                LocalDateTime.now()
                        .plusMinutes(mins)
                        .atZone(CTT_ZONE_ID)
                        .toInstant()
                , CTT_DATE_TIME_FORMATTER);
    }

    /**
     * 获取指定几天前的String时间
     *
     * @return
     */
    public static String getMinusDayStringDate(Integer days) {
        return toStringDate(
                LocalDateTime.now()
                        .minusDays(days)
                        .atZone(CTT_ZONE_ID)
                        .toInstant()
                , CTT_DATE_TIME_FORMATTER);
    }

    /**
     * 获取指定几天前的String时间  从00:00:00开始
     *
     * @return
     */
    public static String getMinusDayStringDateZero(Integer days) {
        return toStringDate(
                LocalDateTime.now()
                        .minusDays(days)
                        .atZone(CTT_ZONE_ID)
                        .toInstant()
                , CTT_DATE_FORMATTER) + " 00:00:00";
    }

    /**
     * 获取指定时间几天前的String时间   从00:00:00开始
     *
     * @return
     */
    public static String getMinusDayStringDateZero(String dateTime, Integer days) {
        Long time = toEpochMilli(dateTime) - days * 24 * 60 * 60;
        Instant instant = Instant.ofEpochMilli(time * 1000);
        return toStringDate(instant, CTT_DATE_FORMATTER) + " 00:00:00";
    }


    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static Long getCurrentEpochMilli() {
        return getCurrent().toEpochMilli() / 1000;
    }

    /**
     * 指定(字符串)时间转为时间戳  秒级
     *
     * @param timeStr
     * @return
     */
    public static Long toEpochMilli(String timeStr) {
        return toInstant(timeStr).toEpochMilli() / 1000;
    }

    /**
     * 指定(日期)时间转为时间戳  秒级
     *
     * @param date
     * @return
     */
    public static Long toEpochMilli(Date date) {
        return toInstant(date).toEpochMilli() / 1000;
    }

    public static Long toEpochMilli(Instant time) {
        return time.toEpochMilli();
    }

    /**
     * 格式化输出时间字符串，上海时区,默认DateTimeFormatter
     */
    public static String toStringDate(Instant instant) {
        return toStringDate(instant, CTT_DATE_TIME_FORMATTER);
    }

    /**
     * 格式化输出时间字符串，上海时区,默认DateTimeFormatter
     */
    public static Date toDate(Instant instant) {
        return new Date(instant.toEpochMilli());
    }

    /**
     * 格式化输出时间，上海时区,默认DateTimeFormatter
     */
    public static Instant toInstant(String timeStr) {
        if (StringUtils.isEmpty(timeStr)) {
            return null;
        }
        return toInstant(timeStr, CTT_DATE_TIME_FORMATTER);
    }

    /**
     * 格式化输出时间，上海时区,默认DateTimeFormatter
     */
    public static Instant toInstantIso(String timeStr) {
        if (StringUtils.isEmpty(timeStr)) {
            return null;
        }
        return toInstant(timeStr, CTT_DATE_ISO_FORMATTER);
    }

    /**
     * 格式化输出时间字符串，上海时区,指定DateTimeFormatter
     */
    public static String toStringDate(Instant instant, DateTimeFormatter dateTimeFormatter) {
        return LocalDateTime
                .ofInstant(instant, CTT_ZONE_ID)
                .format(dateTimeFormatter);
    }

    /**
     * 格式化输出时间字符串，上海时区,指定DateTimeFormatter
     */
    public static String toStringDateTime(Instant instant) {
        return toStringDate(instant, CTT_DATE_TIME_FORMATTER);
    }

    /**
     * 格式化输出时间，上海时区,指定DateTimeFormatter
     */
    public static Instant toInstant(String timeStr, DateTimeFormatter dateTimeFormatter) {
        return LocalDateTime
                .parse(timeStr, dateTimeFormatter)
                .atZone(CTT_ZONE_ID)
                .toInstant();
    }

    /**
     * 格式化输出时间，上海时区
     */
    public static String toStringDate(String timeStr) {
        return YDateUtil.toStringDate(YDateUtil.toInstant(timeStr));
    }

    /**
     * 格式化输出时间，上海时区
     */
    public static String toStringDate(String timeStr, String formatter) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);
        Instant instant = toInstant(timeStr);
        return LocalDateTime
                .ofInstant(instant, CTT_ZONE_ID)
                .format(dateTimeFormatter);
    }

    /**
     * ISO格式化输出时间，上海时区
     */
    public static String toStringDateIso(String timeStr) {
        Instant instant = toInstantIso(timeStr);
        return LocalDateTime
                .ofInstant(instant, CTT_ZONE_ID)
                .format(CTT_DATE_TIME_FORMATTER);
    }

    /**
     * date 转 instant
     *
     * @param date
     * @return
     */
    public static Instant toInstant(Date date) {
        return Instant.ofEpochMilli(date.getTime());
    }

    /**
     * 格式 化日期，返回符合格式的字符串
     *
     * @param date
     * @param formatStr 默认 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String dateToString(Date date, String formatStr) {
        if (formatStr == null || "".equals(formatStr.trim())) {
            formatStr = "yyyy-MM-dd HH:mm:ss";
        }
        if (date == null) {
            return "";
        }
        String str = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatStr);
            str = format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 格式化日期字符串，返回符合格式的date
     *
     * @param dateStr
     * @param formater 如:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date StringToDate(String dateStr, String formater) {
        Date date = null;
        if (dateStr == null || "".equals(dateStr.trim())) {
            return date;
        }
        DateFormat format;
        if (formater == null || "".equals(formater.trim())) {
            format = commonFormat.get();
        } else {
            format = new SimpleDateFormat(formater);
        }

        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Date按通用格式‘yyyy-MM-dd HH:mm:ss’转String
     *
     * @param date
     * @return
     */
    public static String commonFormat(Date date) {
        return commonFormat.get().format(date);
    }

    /**
     * String 按通用格式‘yyyy-MM-dd HH:mm:ss’转Date
     *
     * @param dateStr
     * @return
     */
    public static Date commonFormat(String dateStr) {
        return StringToDate(dateStr, FORMAT_STR);
    }

    /**
     * 获取当日零点的时间戳
     *
     * @return
     */
    public static Integer getCurrentDateZero() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Long date = calendar.getTime().getTime();
        return date.intValue();
    }


    /**
     * 获取当日23:59:59的时间戳
     *
     * @return
     */
    public static Integer getCurrentDateEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Long date = calendar.getTime().getTime();
        return date.intValue();
    }



    /**
     * 将日期增减 n 月
     *
     * @param date   参照日期,如果为null则取当前日期
     * @param months 增减的天数 (为正数则增加，为负数则减少)
     * @return Date
     */
    public static Date addMonth(Date date, int months) {
        return addDate(date, months, Calendar.MONTH);
    }

    /**
     * 将日期增减 n 天
     *
     * @param date 参照日期,如果为null则取当前日期
     * @param days 增减的天数 (为正数则增加，为负数则减少)
     * @return Date
     */
    public static Date addDate(Date date, int days) {
        return addDate(date, days, Calendar.DATE);
    }

    /**
     * 将日期增减 n 分钟
     *
     * @param date    参照日期,如果为null则取当前日期
     * @param minutes 增减的分钟 (为正数则增加，为负数则减少)
     * @return Date
     */
    public static Date addMin(Date date, int minutes) {
        return addDate(date, minutes, Calendar.MINUTE);
    }

    /**
     * 取某一日期增减 n 值后的日期, n 由 dateField 决定是年、月、日 根据增加or减少的时间得到新的日期
     *
     * @param date      参照日期
     * @param counts    增减的数值
     * @param dateField int 需操作的日期字段, 取值范围如下 Calendar.YEAY 年 Calendar.MONTH 月
     *                  Calendar.DATE 日 .... Calendar.SECOND 秒
     * @return Date
     */
    private static Date addDate(Date date, int counts, int dateField) {
        Date dateTime = null;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateField, counts);
            dateTime = calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    /**
     * 格林尼治时间longTimme
     *
     * @param date
     * @return
     */
    public static long getTimeInMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    /**
     * 计算两个时间相差多少分钟
     *
     * @param startDateStr
     * @param endDateStr
     * @return
     */
    public static int differMinuteTime(String startDateStr, String endDateStr, String formater) {
        int differMinute = 0;
        try {
            Date startDate = YDateUtil.StringToDate(startDateStr, formater);
            Date endDate = YDateUtil.StringToDate(endDateStr, formater);
            long startTimeInMillis = getTimeInMillis(startDate);
            long endTimeInMillis = getTimeInMillis(endDate);
            differMinute = (int) ((endTimeInMillis - startTimeInMillis) / 1000 / 60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return differMinute;
    }


    /**
     * 获取两个日期之间全部天数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getBetweenDate(String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 声明保存日期集合
        List<String> list = new ArrayList<String>();
        try {
            // 转化成日期类型
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);

            //用Calendar 进行日期比较判断
            Calendar calendar = Calendar.getInstance();
            while (startDate.getTime() <= endDate.getTime()) {
                // 把日期添加到集合
                list.add(sdf.format(startDate));
                // 设置日期
                calendar.setTime(startDate);
                //把日期增加一天
                calendar.add(Calendar.DATE, 1);
                // 获取增加后的日期
                startDate = calendar.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Integer getMinBetweenDate(Date startTime, Date endTime) {
        return getMinBetweenDate(commonFormat(startTime), commonFormat(endTime));
    }

    /**
     * 获取两个时间之间的分钟
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Integer getMinBetweenDate(String startTime, String endTime) {
        Long start = toEpochMilli(startTime);
        Long end = toEpochMilli(endTime);

        Long mins = (end - start) / 60;

        return mins.intValue();
    }

    /**
     * 获取当前月的第一天零点
     *
     * @return
     */
    public static Date getCurrentMonthFirstDayZero() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY, 0);//设置为1号,当前日期既为本月第一天
        c.set(Calendar.MINUTE, 0);//设置为1号,当前日期既为本月第一天
        c.set(Calendar.SECOND, 0);//设置为1号,当前日期既为本月第一天
        return c.getTime();
    }

    /**
     * 判断两个日期是否在同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }
}
