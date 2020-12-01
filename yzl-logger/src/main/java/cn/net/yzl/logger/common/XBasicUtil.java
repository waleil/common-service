package cn.net.yzl.logger.common;

import cn.net.yzl.logger.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @title: XBasicUtil
 * @Author lichanghong
 * @Date: 2020/11/30 9:36 上午
 * @Version 1.0
 */
@Slf4j
public class XBasicUtil {

    public static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    public static final String dateNoYear2Format = "MM月dd日HH:mm";
    public static final ThreadLocal<SimpleDateFormat> dateFormatLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat(dateTimeFormat));
    public static final ThreadLocal<SimpleDateFormat> dateFormatNoYear2 = ThreadLocal.withInitial(() -> new SimpleDateFormat(dateNoYear2Format));

    public static boolean getBoolean(Object bool, boolean defaultValue) {
        boolean b = defaultValue;
        try {
            b = (null == bool ? defaultValue : Boolean.parseBoolean(bool.toString()));
        } catch (Exception e) {
            log.error("XBeanUtil.getBoolean error|traceId:{}", Log.getTraceId() , e);
        }
        return b;
    }

    public static int getInt(Object value, int defaultValue) {
        int r = defaultValue;
        try {
            r = (null == value ? defaultValue : Integer.parseInt(value.toString()));
        } catch (Exception ex) {
            log.error("XBeanUtil.getInt error|traceId:{}", Log.getTraceId(), ex);
            return r;
        }
        return r;
    }

    public static int compare(Integer a, Integer b) {
        return getInt(a, 0) - getInt(b, 0);
    }

    public static String getStr(Object value, String defaultValue) {
        return null == value ? defaultValue : value.toString();
    }

    public static String getStrNotBlank(String value, String defaultValue) {
        return (StringUtils.isBlank(value)) ? defaultValue : value;
    }

    public static String getStrNotBlank(Object... value) {
        for (int i = 0; null != value && i < value.length; i++) {
            if (null != value[i] && StringUtils.isNotBlank(value[i].toString())) {
                return value[i].toString();
            }
        }
        return null;
    }

    public static boolean isAllValuesBlank(Map<String, String> rowData) {
        if (null == rowData || rowData.isEmpty()) {
            return true;
        }
        for (String k : rowData.keySet()) {
            if (StringUtils.isNotBlank(rowData.get(k))) {
                return false;
            }
        }
        return true;
    }

    public static <T> T toObject(Map src, Class<T> type) {
        T t = BeanUtils.instantiate(type);
        BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(t);
        wrapper.setAutoGrowNestedPaths(true);
        wrapper.setPropertyValues(src);
        return t;
    }

    public static Date getDay() {
        Date date = new Date();
        DateUtils.truncate(date, Calendar.HOUR_OF_DAY);
        DateUtils.truncate(date, Calendar.MINUTE);
        DateUtils.truncate(date, Calendar.MILLISECOND);
        return date;
    }

    public static Date getDay(Date date) {
        if (null == date) {
            date = new Date();
        }
        DateUtils.truncate(date, Calendar.HOUR_OF_DAY);
        DateUtils.truncate(date, Calendar.MINUTE);
        DateUtils.truncate(date, Calendar.MILLISECOND);
        return date;
    }

    public static String getDayStr() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd");
    }

    public static String getDayStr(Date date) {
        date = (null == date) ? new Date() : date;
        return DateFormatUtils.format(date, "yyyy-MM-dd");
    }

    public static String getDateTimeStr() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getDateTimeStr(Date date) {
        date = (null == date) ? new Date() : date;
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getDateTimeStr(Date date, String defaultValue) {
        if (null == date) {
            return defaultValue;
        }
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getTimeStr() {
        return DateFormatUtils.format(new Date(), "HH:mm:ss");
    }

    public static String getTimeStr(Date date) {
        date = (null == date) ? new Date() : date;
        return DateFormatUtils.format(date, "HH:mm:ss");
    }

    public static String getDayOfWeek(Date date) {
        date = (null == date) ? new Date() : date;
        return DateFormatUtils.format(date, "EEEE");
    }

    public static List<String> getDateTimeStr4OAuth() {
        Date a = new Date();
        Date b = DateUtils.addHours(a, -1);
        Date c = DateUtils.addHours(a, 1);
        return Arrays.asList(
                DateFormatUtils.format(a, "yyyyMMddHH"),
                DateFormatUtils.format(b, "yyyyMMddHH"),
                DateFormatUtils.format(c, "yyyyMMddHH")
        );
    }

    public static String uuid() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }

    public static String upper(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        } else {
            return str.toUpperCase();
        }
    }

    public static List<String> upper(List<String> list) {
        return XCollectionUtil.evalIfExists(list, String::toUpperCase);
    }

    public static String messageTemplate(String template, Map<String, String> kv) {
        if (StringUtils.isBlank(template) || null == kv) {
            return template;
        } else {
            for (String k : kv.keySet()) {
                template = template.replaceAll(Pattern.quote("#{" + k + "}"), kv.get(k));
            }
            return template;
        }
    }

    public static String keyOf(String separator, Object... args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; null != args && i < args.length; i++) {
            sb.append(String.valueOf(args[i]));
            if (i < args.length - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    public static String keyOfHash(String separator, Object... args) {
        return XSecurityUtil.encodeByMD5(keyOf(separator, args));
    }

    public static boolean mkdir(String path) {
        try {
            FileUtils.forceMkdir(new File(path));
            return true;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    public static URI uri(String src) {
        URI uri = null;
        try {
            uri = new URI("http://localhost:8080");
            uri = new URI(src);
        } catch (Exception ex) {
            log.error("XBasicUtil.uri error => {} | traceId:{}" , src, Log.getTraceId(), ex);
        }
        return uri;
    }

    public static String getClientIp(HttpServletRequest request) {
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static boolean ignoreRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return uri.endsWith("/error")
                || uri.equalsIgnoreCase("/")
                || isStatic(uri);
    }

    public static boolean isStatic(String uri) {
        return uri.contains(".ico")
                || uri.contains(".png")
                || uri.contains(".gif")
                || uri.contains(".pdf")
                || uri.contains(".js")
                || uri.contains(".css")
                || uri.contains(".html")
                || uri.contains(".ttf")
                || uri.contains(".woff")
                || uri.contains(".xlsx")
                ;
    }

    public static boolean checkNewOldParam(String studentCode, String studentName, String studentPhone) {
        if (StringUtils.isEmpty(studentCode) && XCollectionUtil.isAnyEmpty(studentName, studentPhone)) {
            return false;
        }
        return true;
    }
}
