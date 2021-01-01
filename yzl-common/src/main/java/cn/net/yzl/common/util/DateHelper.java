package cn.net.yzl.common.util;

import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateHelper {
    public static Date getCurrentDate() {
        Date date = new Date();
        return ConvertHelper.toDate(formatDate(date, "yyyy-MM-dd HH:mm:ss"));
    }

    public static String getCurrentDateStr(String format) {
        Date date = new Date();
        return formatDate(date, format);
    }

    public static String formatDate(Date date, String format) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }
        return "";
    }


    public static int getCurrentQuarter() {
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int quarter = 0;
        if (month >= 1 && month <= 3) {
            quarter = 1;
        }
        if (month >= 4 && month <= 6) {
            quarter = 2;
        }
        if (month >= 7 && month <= 9) {
            quarter = 3;
        }
        if (month >= 10 && month <= 12) {
            quarter = 4;
        }
        return quarter;
    }

    public static long dateToStamp(String dateStr) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = simpleDateFormat.parse(dateStr);
        long ts = date.getTime();
        return ts;
    }
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getYearByDate(Date dt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        return calendar.get(Calendar.YEAR);
    }
    public static int getModthByDate(Date dt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        return calendar.get(Calendar.MONTH);
    }

    public static long timeDiff(String unit, Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        //一天的毫秒数 86400000 = 24*60*60*1000;
        int millisPerDay = 86400000;
        //一小时的毫秒数 3600000 = 24*60*60*1000;
        int millisPerHour = 3600000;
        //时间单位(如：不足1天(24小时) 则返回0)，开始时间，结束时间
        long ltime = date1.getTime() - date2.getTime() < 0 ? date2.getTime() - date1.getTime() : date1.getTime() - date2.getTime();
        if (unit.equals("s")) {
            return ltime / 1000;//返回秒
        } else if (unit.equals("m")) {
            return ltime / 60000;//返回分钟
        } else if (unit.equals("h")) {
            return ltime / millisPerHour;//返回小时
        } else if (unit.equals("d")) {
            return ltime / millisPerDay;//返回天数
        } else {
            return 0;
        }
    }

    /**
     * 获取两个之间差值
     */
    public static long timeDiffValue(String unit, Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        //一天的毫秒数 86400000 = 24*60*60*1000;
        int millisPerDay = 86400000;
        //一小时的毫秒数 3600000 = 24*60*60*1000;
        int millisPerHour = 3600000;
        //时间单位(如：不足1天(24小时) 则返回0)，开始时间，结束时间
        long ltime = date1.getTime() - date2.getTime() < 0 ? 0 : date1.getTime() - date2.getTime();

        if (unit.equals("s")) {
            return ltime / 1000;//返回秒
        } else if (unit.equals("m")) {
            return ltime / 60000;//返回分钟
        } else if (unit.equals("h")) {
            return ltime / millisPerHour;//返回小时
        } else if (unit.equals("d")) {
            return ltime / millisPerDay;//返回天数
        } else {
            return 0;
        }
    }

    public static Date timeAdd(Date date, String unit, int count) {
        if (unit.equals("s")) {
            return DateUtils.addSeconds(date, count);
        } else if (unit.equals("m")) {
            return DateUtils.addMinutes(date, count);
        } else if (unit.equals("h")) {
            return DateUtils.addHours(date, count);
        } else if (unit.equals("d")) {
            return DateUtils.addDays(date, count);
        } else {
            return date;
        }
    }

    /// <summary>
    /// 获取时间戳13位，到毫秒
    /// </summary>
    /// <returns></returns>
    public static long getTimestamp() {
        return DateHelper.getCurrentDate().getTime();
    }

    /// <summary>
    /// 获取10位时间戳,到秒
    /// </summary>
    /// <returns></returns>
    public static long getTimestamp10() {
        String timestamp = String.valueOf(DateHelper.getCurrentDate().getTime() / 1000);
        return Integer.valueOf(timestamp);
    }

    /**
     * Date类型转换为10位时间戳
     *
     * @param time
     * @return
     */
    public static long DateToTimestamp(Date time) {
        Timestamp ts = new Timestamp(time.getTime());

        return (int) ((ts.getTime()) / 1000);
    }


    public static long DateToTimestamp1(Date time) {
        Timestamp ts = new Timestamp(time.getTime());

        return ts.getTime();
    }
    public static String formateDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
            return sdf.format(date);
        }
        return "";
    }

    public static final SimpleDateFormat STD_DATE_FORMAT;
    public static final SimpleDateFormat STD_TIME_FORMAT;
    public static final SimpleDateFormat STD_DATE_TIME_FORMAT;
    public static final SimpleDateFormat STD_SHORT_DATE_TIME_FORMAT;
    public static final SimpleDateFormat STD_DATE_TIME_FORMATX;
    //
    public static final SimpleDateFormat FILE_DATE_TIME_FORMAT;
    public static final SimpleDateFormat INT_DATE_FORMAT;

    public static final String COMMON_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_ESPWD = "yyyyMMddHHmmss";
    public static final String SIMPLE_DATE = "yyyy-MM-dd";
    public static final String STD_DATE_TIME_FORMATX_SSS="yyyy-MM-dd HH:mm:ss.SSS";
    public static final String STD_DATE_TIME_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    static {
        STD_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        STD_TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
        STD_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        STD_SHORT_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        STD_DATE_TIME_FORMATX = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        //
        FILE_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        INT_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    }

    // 默认时区"GMT+08:00"
    public final static TimeZone defaultTimezone = TimeZone.getTimeZone("GMT+08:00");

    public static Calendar getCalendar(TimeZone timeZone) {
        if (timeZone == null) {
            timeZone = defaultTimezone;
        }
        return Calendar.getInstance(defaultTimezone);
    }

    /**
     * 获取当前零时区时间
     * @param format,timeStamp
     * @return
     */
    public static  String zeroZoneTime(long timeStamp,String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        return dateFormat.format(new Date(timeStamp));
    }

    /* //时间戳转换日期 */
    public static String stampToTime() {
        String sd = "";
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        sd = sdf.format(new Date()); // 时间戳转换日期
        return sd;
    }
    public static Calendar getCalendar() {
        return getCalendar(null);
    }

    public static boolean isLeapYear(int year) {
        return year % 400 == 0 || year % 4 == 0 && year % 100 != 0;
    }

    public static String toStdDateStr(Date date) {
        return date == null ? null : STD_DATE_FORMAT.format(date);
    }

    public static String toStdTimeStr(Date date) {
        return date == null ? null : STD_TIME_FORMAT.format(date);
    }

    public static String toStdDateTimeStr(Date date) {
        return date == null ? null : STD_DATE_TIME_FORMAT.format(date);
    }

    public static String toStdShortDateTimeStr(Date date) {
        return date == null ? null : STD_SHORT_DATE_TIME_FORMAT.format(date);
    }

    public static String toStdDateTimeXStr(Date date) {
        return date == null ? null : STD_DATE_TIME_FORMATX.format(date);
    }

    public static String toFileDateTimeStr(Date date) {
        return date == null ? null : FILE_DATE_TIME_FORMAT.format(date);
    }

    public static String toFileDateTimeStr() {
        return toFileDateTimeStr(new Date());
    }

    public static Integer toInteger(Date date) {
        return date == null ? null : Integer.valueOf(INT_DATE_FORMAT.format(date));
    }

    public static Date fromStdDateTimeXStr(String dtStr) throws ParseException {
        try {
            return STD_DATE_TIME_FORMATX.parse(dtStr);
        } catch (Exception e) {
        }
        return null;
    }

    public static Date fromStdDateTimeStr(String dtStr) throws ParseException {
        try {
            return STD_DATE_TIME_FORMAT.parse(dtStr);
        } catch (Exception e) {
            dtStr = dtStr.substring(0, dtStr.length() - 4);
            try {
                return STD_DATE_FORMAT.parse(dtStr);
            } catch (Exception ex) {
            }
        }
        return null;
    }


    public static Date fromStdShortDateTimeStr(String dtStr) throws ParseException {
        return STD_SHORT_DATE_TIME_FORMAT.parse(dtStr);
    }

    public static Date fromStdDateStr(String dtStr) throws ParseException {
        return STD_DATE_FORMAT.parse(dtStr);
    }

    public static Date fromTimeStamp(String timestamp) {
        try {
            return new Date(Long.parseLong(timestamp));
        } catch (Exception ex) {
            return new Date();
        }
    }

    /**
     * 日期计算
     *
     * @param refDate
     * @param amount
     * @return
     */
    public static Date addYears(Date refDate, int amount) {
        return DateUtils.addYears(refDate, amount);
    }

    public static Date addYears(int amount) {
        Date refDate = new Date();
        return org.apache.commons.lang3.time.DateUtils.addYears(refDate, amount);
    }

    public static Date addMonths(Date refDate, int amount) {
        return org.apache.commons.lang3.time.DateUtils.addMonths(refDate, amount);
    }

    public static Date addMonths(int amount) {
        Date refDate = new Date();
        return DateUtils.addMonths(refDate, amount);
    }

    public static Date addDays(Date refDate, int amount) {
        return DateUtils.addDays(refDate, amount);
    }

    public static Date addDays(int amount) {
        Date refDate = new Date();
        return DateUtils.addDays(refDate, amount);
    }

    public static Date addHours(Date refDate, int amount) {
        return DateUtils.addHours(refDate, amount);
    }

    public static Date addHours(int amount) {
        Date refDate = new Date();
        return DateUtils.addHours(refDate, amount);
    }

    public static Date addMinutes(Date refDate, int amount) {
        return DateUtils.addMinutes(refDate, amount);
    }

    public static Date addMinutes(int amount) {
        Date refDate = new Date();
        return DateUtils.addMinutes(refDate, amount);
    }

    public static Date addSeconds(Date refDate, int amount) {
        return DateUtils.addSeconds(refDate, amount);
    }

    public static Date addSeconds(int amount) {
        Date refDate = new Date();
        return DateUtils.addSeconds(refDate, amount);
    }

    public static Date addWeeks(Date refDate, int amount) {
        return DateUtils.addWeeks(refDate, amount);
    }

    public static Date addWeeks(int amount) {
        Date refDate = new Date();
        return DateUtils.addWeeks(refDate, amount);
    }


    private static String msg = "";

    /**
     * 根据给定格式得到当前日期时间
     *
     * @param fmt 需要的日期格式
     * @return 符合格式要求的日期字符串 返回格式一般应为yyyy-MM-dd HH:mm:ss
     */
    public static String getDate(String fmt) {
        Date myDate = new Date(System.currentTimeMillis());
        SimpleDateFormat sDateformat = new SimpleDateFormat(fmt);
        sDateformat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return sDateformat.format(myDate);
    }


    /**
     * 将指定日期格式化
     *
     * @param fmt
     * @param date
     * @return
     */
    public static String getDate(String fmt, Date date) {
        SimpleDateFormat sDateformat = new SimpleDateFormat(fmt);
        return sDateformat.format(date);
    }

    public static String getDateByStr(String fromFmt, String toFmt, String dateStr) {
        try {
            SimpleDateFormat from = new SimpleDateFormat(fromFmt);
            SimpleDateFormat to = new SimpleDateFormat(toFmt);
            Date temp;
            temp = from.parse(dateStr);
            dateStr = to.format(temp);
            return dateStr;
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 将指定日期格式化
     *
     * @param fmt
     * @param dateStr
     * @return
     */
    public static String getDateByStr(String fmt, String dateStr) {
        SimpleDateFormat sDateformat = new SimpleDateFormat(fmt);
        try {
            Date dates = sDateformat.parse(dateStr);
            return sDateformat.format(dates);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStr;
    }


    public static String getCommonDateStr(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(COMMON_DATE_FORMAT);
        return sdf.format(calendar.getTime());
    }

    public static Calendar getCommonDate(String dateStr) {
        try {
            return getCal(dateStr, COMMON_DATE_FORMAT);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 把给定的日期字符串转换成Calendar型
     *
     * @param strdate 给定日期字符串
     * @param fmt     给定日期字符串的格式
     * @return 初始化好的Calendar类
     * @throws ParseException
     */

    public static Calendar getCal(String strdate, String fmt) {
        Calendar cal = null;
        try {
            // 判断给定参数是否为空，如果空则返回参数错误消息
            if (strdate == null || fmt == null) {
                return null;
            }
            // 根据给定日期格式得到当前时间
            SimpleDateFormat nowDate = new SimpleDateFormat(fmt);
            // 转换为格式为Date类型
            Date d = nowDate.parse(strdate, new java.text.ParsePosition(0));
            // 如果转换返回Date为空则抛出参数解析错误消息
            if (d == null) {
                return null;
            }
            // 得到一个Calendar实例
            cal = Calendar.getInstance();
            // Calendar日期归零
            cal.clear();
            // 设定当前时间
            cal.setTime(d);
        } catch (Exception e) { //此处应该捕获ParseException，由于采用了ParsePosition(0)格式所以此异常不用捕获
            e.printStackTrace();
        }
        return cal;
    }

    /**
     * 给定日期所在周是给定年的第几周
     *
     * @param strdate 给定的日期字符串
     * @param fmt     给定日期的格式
     * @return 返回整型数字
     */
    public static int getWeekOfYear(String strdate, String fmt) {
        // 返回值初始化，指定一个小于0的不可能得到的数字
        int ret = -1;
        try {
            // 判断给定参数是否为空，如果空则返回参数错误消息
            if (strdate == null || fmt == null) {
                return ret;
            }
            // 根据给定参数转换为Calendar类型
            Calendar cal = getCal(strdate, fmt);
            // 转换异常则返回错误消息
            if (cal == null) {
                return ret;
            }
            // 得到给定日期所在周是给定年的第几周
            ret = cal.get(Calendar.WEEK_OF_YEAR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 给定日期所在周的全部日期
     *
     * @param strdate 给定的日期字符串
     * @param oldfmt  给定日期的格式
     * @param newfmt  返回日期的格式
     * @return 从周一开始到周日的日期
     */
    @SuppressWarnings("static-access")
    public static String[] getWeekDay(String strdate, String oldfmt, String newfmt) {
        String weekday[] = new String[7];
        try {
            // 判断给定参数是否为空，如果空则返回参数错误消息
            if (strdate == null || oldfmt == null || newfmt == null) {
                return null;
            }
            // 根据给定参数转换为Calendar类型
            Calendar cal = getCal(strdate, oldfmt);
            // 转换异常则返回错误消息
            if (cal == null) {
                return null;
            }
            // 得到给定日期是本周的的几天
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            // 修改为中国习惯，从周一开始算一周时间
            cal.add(cal.DATE, -dayOfWeek + 2);
            // 根据参数设定返回格式
            SimpleDateFormat sdf = new SimpleDateFormat(newfmt);
            // 循环得到一周的时间
            weekday[0] = sdf.format(cal.getTime());
            for (int i = 1; i < 7; i++) {
                cal.add(cal.DATE, 1);
                weekday[i] = sdf.format(cal.getTime());
            }
        } catch (IndexOutOfBoundsException iobe) {
            iobe.printStackTrace();
        }
        return weekday;
    }

    /**
     * 本方法完成得到给定周的全部日期
     *
     * @param year   给定年
     * @param week   给定周
     * @param newfmt 返回日期的格式
     * @return 从周一开始到周日的日期
     */
    public static String[] getWeekDate(String year, int week, String newfmt) {
        String jweekday[] = new String[7];
        try {
            // 判断给定参数是否为空，如果空则返回参数错误消息
            if (year == null || year.length() < 4 || week <= 0 || newfmt == null) {
                return null;
            }
            // 根据给定参数转换为Calendar类型,起始计算时间调整为当年的1月1日
            Calendar cal = getCal(year + "0101", "yyyyMMdd");
            // 转换异常则返回错误消息
            if (cal == null) {
                return null;
            }
            // java类的周计算从0开始，调整正常习惯为java描述需要减1
            week = week - 1;
            // 日期调整至当给定周的第一天
            cal.add(Calendar.DATE, week * 7 - cal.get(Calendar.DAY_OF_WEEK) + 2);
            // 根据参数设定返回格式
            SimpleDateFormat sdf = new SimpleDateFormat(newfmt);
            jweekday[0] = sdf.format(cal.getTime());
            // 循环得到一周的时间
            for (int i = 1; i < 7; i++) {
                cal.add(Calendar.DATE, 1);
                jweekday[i] = sdf.format(cal.getTime());
            }
        } catch (IndexOutOfBoundsException iobe) {
            iobe.printStackTrace();
        }
        return jweekday;
    }


    /**
     * 本方法完成计算给定时间之前一段时间的日期时间
     *
     * @param deftime  给定时间字符串
     * @param timediff 以小时为单位
     * @param oldfmt   给定时间的格式
     * @param newfmt   返回时间的格式
     * @return 时间字符串
     */
    public static String getBeforeTime(String deftime, String oldfmt, int timediff, String newfmt) {
        String strBeforeTime = null;
        try {
            // 判断给定参数是否为空，如果空则返回参数错误消息
            if (deftime == null || deftime.equals("")) {
                return null;
            }
            // 根据给定参数转换为Calendar类型
            Calendar cal = getCal(deftime, oldfmt);
            // 转换异常则返回错误消息
            if (cal == null) {
                return null;
            }
            // 以分钟计算之前的日期
            cal.add(Calendar.MINUTE, -timediff * 60);
            // 根据参数设定返回格式
            SimpleDateFormat sdf = new SimpleDateFormat(newfmt);
            // 格式化返回日期
            strBeforeTime = sdf.format(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strBeforeTime;
    }

    /**
     * 获取给定时间之后具体天数的时间
     *
     * @param deftime  String  指定的时间
     * @param oldfmt   String   指定的时间格式
     * @param timediff int    天数
     * @param newfmt   String   指定的时间格式
     * @return String
     */
    public static String getAfterTime(String deftime, String oldfmt, int timediff, String newfmt) {
        String strAfterTime = null;
        try {
            // 判断给定参数是否为空，如果空则返回参数错误消息
            if (deftime == null || deftime.equals("")) {
                return null;
            }
            // 根据给定参数转换为Calendar类型
            Calendar cal = getCal(deftime, oldfmt);
            // 转换异常则返回错误消息
            if (cal == null) {
                return null;
            }
            // 以分钟计算之后的日期
            cal.add(Calendar.MINUTE, +timediff * 60);
            // 根据参数设定返回格式
            SimpleDateFormat sdf = new SimpleDateFormat(newfmt);
            // 格式化返回日期
            strAfterTime = sdf.format(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strAfterTime;
    }

    /**
     * 本方法完成把给定字符串转换成需要格式，如果不够位数则自动0补满位
     *
     * @param mydate 给定时间
     * @param oldfmt 给定时间格式
     * @param newfmt 返回时间格式
     * @return 字符串
     */
    public static String fmtDate(String mydate, String oldfmt, String newfmt) {
        String restr = null;
        try {
            // 判断给定参数是否为空，如果空则返回参数错误消息
            if (mydate == null || oldfmt == null || newfmt == null) {
                return null;
            }
            SimpleDateFormat newDate = new SimpleDateFormat(newfmt);
            // 根据给定参数转换为Calendar类型
            Calendar cal = getCal(mydate, oldfmt);
            // 转换异常则返回错误消息
            if (cal == null) {
                return null;
            }
            // 按给定格式返回
            restr = newDate.format(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restr;
    }

    //判断当前时间是否在时间date之前
    //时间格式 2005-4-21 16:16:34
    public static boolean isDateBefore(String date) {
        try {
            Date date1 = new Date();
            DateFormat df = DateFormat.getDateTimeInstance();
            return date1.before(df.parse(date));
        } catch (ParseException e) {
            return false;
        }
    }

    //判断当前时间是否在时间date之前
    //时间格式 2005-4-21 16:16:34
    public static boolean isDateBetween(String time, String startTime, String endTime) {
        try {
            DateFormat df = DateFormat.getDateTimeInstance();
            Date date1 = df.parse(time);
            if (date1.before(df.parse(endTime)) && date1.after(df.parse(startTime))) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 判断是否超时
     *
     * @param beginDate     long
     * @param TimeOutmillis long
     * @return boolean
     */
    public static boolean whileUnitTimerOut(long beginDate, long TimeOutmillis) {
        long currentDate = System.currentTimeMillis();
        long endDate = beginDate + TimeOutmillis;
        return currentDate <= endDate;
    }

    /**
     * 格式化时间
     *
     * @param dateString String
     * @return Date
     */
    public static Date formatDateString(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = null;
        try {
            startDate = sdf.parse(dateString);
        } catch (ParseException ex) {
            startDate = null;
        }
        return startDate;
    }

    /**
     * 格式化时间
     *
     * @param formatString
     * @param dateString
     * @return
     */
    public static Date formatDateString(String formatString, String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        Date startDate = null;
        try {
            startDate = sdf.parse(dateString);
        } catch (ParseException ex) {
            startDate = null;
        }
        return startDate;
    }

    /*
     * 判断当前的时间 密码是否过期。
     * pwdModDate 密码最后修改一次的时间
     * policyDate 密码策略的制定的天数
     * 现在的时间在周期的后的时间返回true 密码过期
     */
    public static Boolean pwdIsOverdue(String pwdModDate, String policyDate) {
        String expiredDateStr = "";
        int policy = Integer.parseInt(policyDate);
        try {
            expiredDateStr = DateHelper.getAfterTime(pwdModDate, "yyyy-MM-dd HH:mm:ss", policy * 24, "yyyy-MM-dd HH:mm:ss");
            Calendar expiredDate = DateHelper.getCal(expiredDateStr, "yyyy-MM-dd HH:mm:ss");
            String now = DateHelper.getDate("yyyy-MM-dd HH:mm:ss");
            Calendar nowcalendar = DateHelper.getCal(now, "yyyy-MM-dd HH:mm:ss");
            return nowcalendar.before(expiredDate);        //
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 把字符串的时间转变成long型
     *
     * @param str  字符串时间
     * @param mode 字符串时间格式
     * @return Date
     */
    public static long parse(String str, String mode) {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat(mode);
        try {
            date = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date == null ? 0 : date.getTime();
    }

    /**
     * @param date     日期字符串
     * @param patterns 日期格式数组
     * @return
     */
    public static Date parse(Object date, String[] patterns) {
        if (date == null) {
            return null;
        }
        Date result = null;
        for (String pattern : patterns) {
            result = parse(date, pattern);
            if (result != null) {
                break;
            }
        }
        return result;
    }

    public static Date parse(Object date, String pattern) {
        if (date == null) {
            return null;
        }
        Date result = null;
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            result = formatter.parse(date.toString());
        } catch (ParseException e) {
            //e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据给定格式得到指定日期时间
     *
     * @param fmt      需要的日期格式
     * @param datelong 需要转换的long型时间
     * @return
     */
    public static String getLongbyDate(String fmt, long datelong) {
        Date myDate = new Date(datelong);
        SimpleDateFormat sDateformat = new SimpleDateFormat(fmt);
        //sDateformat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return sDateformat.format(myDate);
    }

    /**
     * 根据日期得到周几
     *
     * @param dt
     * @return
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) w = 0;

        return weekDays[w];
    }

    public static int GetQuarter(Date dt) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int month = cal.get(Calendar.MONTH);

        int quarter = 1;
        switch (month) {
            case 1:
            case 2:
            case 3:
                quarter = 1;

                break;
            case 4:
            case 5:
            case 6:
                quarter = 2;
                break;

            case 7:
            case 8:
            case 9:
                quarter = 3;
                break;

            case 10:
            case 11:
            case 12:
                quarter = 4;
                break;
        }
        return quarter;
    }

    /**
     * Calendar.YEAR = 1;
     * Calendar.MONTH = 2
     * Calendar.DATE = 5;
     *
     * @param date
     * @param feild
     * @return
     */
    public static Integer getTimeFeild(Date date, int feild) {
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        return calendar.get(feild);
    }

    //根据出生年月计算年龄
    public static int getAgeByBirth(Date birthDay) throws ParseException {
        int age = 0;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }

    public static Date getDateByString(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(date);
    }

    //根据生日获取星座
    public static String getAstro(Date date) {

// 这个Date是你需要传入的参数Date

        int dateInt = Integer.parseInt(formatDate(date));

        if (dateInt >= 120 && dateInt <= 218) {
            return "水瓶座";
        } else if (dateInt >= 219 && dateInt <= 320) {
            return "双鱼座";
        } else if (dateInt >= 321 && dateInt <= 419) {
            return "白羊座";
        } else if (dateInt >= 420 && dateInt <= 520) {
            return "金牛座";
        } else if (dateInt >= 521 && dateInt <= 620) {
            return "双子座";
        } else if (dateInt >= 621 && dateInt <= 722) {
            return "巨蟹座";
        } else if (dateInt >= 723 && dateInt <= 822) {
            return "狮子座";
        } else if (dateInt >= 823 && dateInt <= 922) {
            return "处女座";
        } else if (dateInt >= 923 && dateInt <= 1022) {
            return "天枰座";
        } else if (dateInt >= 1023 && dateInt <= 1121) {
            return "天蝎座";
        } else if (dateInt >= 1122 && dateInt <= 1221) {
            return "射手座";
        } else {
            return "摩羯座";
        }

    }

    /**
     * Date对象转换为字符串(格式："MMdd")
     *
     * @param date Date
     * @return date为空时皆返回""
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }

        return new SimpleDateFormat("MMdd").format(date);
    }

    //根据时间秒转换为时间格式函数
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    private static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + i;
        else
            retStr = "" + i;
        return retStr;
    }

    /**
     * date2比date1多的天数  精确到天，如包含小时此方法计算不准确
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            //System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }


    /**
     * 给定日期所在周的周一
     * @author ：guowang
     * @param strdate 给定的日期字符串
     * @param oldfmt  给定日期的格式
     * @param newfmt  返回日期的格式
     * @return 从周一开始到周日的日期
     */
    @SuppressWarnings("static-access")
    public static String getWeekFristDay(String strdate, String oldfmt, String newfmt) {
        String weekday=null;
        try {
            // 判断给定参数是否为空，如果空则返回参数错误消息
            if (strdate == null || oldfmt == null || newfmt == null) {
                return null;
            }
            // 根据给定参数转换为Calendar类型
            Calendar cal = getCal(strdate, oldfmt);
            // 转换异常则返回错误消息
            if (cal == null) {
                return null;
            }
            // 得到给定日期是本周的的几天
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            // 修改为中国习惯，从周一开始算一周时间
            cal.add(cal.DATE, -dayOfWeek + 2);
            // 根据参数设定返回格式
            SimpleDateFormat sdf = new SimpleDateFormat(newfmt);
            // 循环得到一周的时间
            weekday = sdf.format(cal.getTime());
        } catch (IndexOutOfBoundsException iobe) {
            iobe.printStackTrace();
        }
        return weekday;
    }
    /**
     * 给定日期所在周的周日
     * @author ：guowang
     * @param strdate 给定的日期字符串
     * @param oldfmt  给定日期的格式
     * @param newfmt  返回日期的格式
     * @return 从周一开始到周日的日期
     */
    @SuppressWarnings("static-access")
    public static String getWeekLastDay(String strdate, String oldfmt, String newfmt) {
        String weekday[] = new String[7];
        String resutl=null;
        try {
            // 判断给定参数是否为空，如果空则返回参数错误消息
            if (strdate == null || oldfmt == null || newfmt == null) {
                return null;
            }
            // 根据给定参数转换为Calendar类型
            Calendar cal = getCal(strdate, oldfmt);
            // 转换异常则返回错误消息
            if (cal == null) {
                return null;
            }
            // 得到给定日期是本周的的几天
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            // 修改为中国习惯，从周一开始算一周时间
            cal.add(cal.DATE, -dayOfWeek + 2);
            // 根据参数设定返回格式
            SimpleDateFormat sdf = new SimpleDateFormat(newfmt);
            // 循环得到一周的时间
            weekday[0] = sdf.format(cal.getTime());
            for (int i = 1; i < 7; i++) {
                cal.add(cal.DATE, 1);
                weekday[i] = sdf.format(cal.getTime());
                if(i==6){
                    resutl=sdf.format(cal.getTime());
                }
            }
        } catch (IndexOutOfBoundsException iobe) {
            iobe.printStackTrace();
        }
        return resutl;
    }

    /**
     * 给定日期所在月的第一天
     *
     * @param strdate 给定的日期字符串
     * @param fmt  给定日期的格式
     * @param newfmt  返回日期的格式
     *  @author ：guowang
     * @return 给定日期所在月的第一天
     */
    public static String getFirstDayDateOfMonth(String strdate,String fmt,String newfmt) {

        String result=null;
        // 根据给定日期格式得到当前时间
        SimpleDateFormat nowDate = new SimpleDateFormat(fmt);
        // 转换为格式为Date类型
        Date d = nowDate.parse(strdate, new java.text.ParsePosition(0));

        Calendar cal = Calendar.getInstance();

        cal.setTime(d);

        int last = cal.getActualMinimum(Calendar.DAY_OF_MONTH);

        cal.set(Calendar.DAY_OF_MONTH, last);

        // 根据参数设定返回格式
        SimpleDateFormat sdf = new SimpleDateFormat(newfmt);
        result = sdf.format(cal.getTime());

        return result;

    }
    /**
     * 给定日期所在月的最后一天
     *
     * @param strdate 给定的日期字符串
     * @param fmt  给定日期的格式
     * @param newfmt  返回日期的格式
     * @author ：guowang
     * @return 给定日期所在月的最后一天
     */
    public static String getLastDayOfMonth(String strdate,String fmt,String newfmt) {
        String result=null;
        // 根据给定日期格式得到当前时间
        SimpleDateFormat nowDate = new SimpleDateFormat(fmt);
        // 转换为格式为Date类型
        Date d = nowDate.parse(strdate, new java.text.ParsePosition(0));

        Calendar cal = Calendar.getInstance();

        cal.setTime(d);

        final int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        cal.set(Calendar.DAY_OF_MONTH, last);
        // 根据参数设定返回格式
        SimpleDateFormat sdf = new SimpleDateFormat(newfmt);
        result = sdf.format(cal.getTime());
        return result;

    }

    /**
     * 功能描述
     * 计算两个时间相差秒数
     * @author ：guowang
     * @date ：2019/8/28 19:53
     */
    public static long getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;

        long nh = 1000 * 60 * 60;

        long nm = 1000 * 60;

        long ns = 1000;

// 获得两个时间的毫秒时间差异

        long diff = endDate.getTime() - nowDate.getTime();

// 计算差多少天

        long day = diff / nd;

// 计算差多少小时

        long hour = diff % nd / nh;

// 计算差多少分钟

        long min = diff % nd % nh / nm;

// 计算差多少秒//输出结果

        long sec = diff % nd % nh % nm / ns;

        return sec;
    }
    public static String getTimeShort() {

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

        Date currentTime = new Date();

        String dateString = formatter.format(currentTime);

        return dateString;
    }
    //当前时间转换为秒
    public static int getDateTimeToSecond(){
        String time =getTimeShort();
        String[] my =time.split(":");
        int hour =Integer.parseInt(my[0]);
        int min =Integer.parseInt(my[1]);
        int sec =Integer.parseInt(my[2]);
        int zong =hour*3600+min*60+sec;
        return zong;
    }
}
