package cn.net.yzl.operatelogger.common;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;


/**
 * @ClassName IpUtil
 * @Descripion Ip工具类
 * @Author liwenjie
 * @Date 2020/12/10 9:57
 **/
public class IpUtil {
    /**
     * 获取真实ip
     *
     * @param request       HttpServletRequest
     * @param acceptInnerIp 是否可以返回内网ip
     * @return 真实ip
     */
    public static String getRemoteIpByServletRequest(HttpServletRequest request, boolean acceptInnerIp) {
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isNotBlank(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.indexOf(",") != -1) {
                ip = ip.split(",")[0];
            }
        }
        if (isIpValid(ip)) {
            return ip;
        }
        ip = request.getHeader("Proxy-Client-IP");
        if (isIpValid(ip)) {
            return ip;
        }
        ip = request.getHeader("WL-Proxy-Client-IP");
        if (isIpValid(ip)) {
            return ip;
        }
        ip = request.getHeader("HTTP_CLIENT_IP");
        if (isIpValid(ip)) {
            return ip;
        }
        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (isIpValid(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Real-IP");
        if (isIpValid(ip)) {
            return ip;
        }
        ip = request.getRemoteAddr();
        return ip;
    }

    /**
     * 判断是否有效
     * @param ip ip
     * @param acceptInnerIp 是否接受内网ip
     * @return
     */
    private static boolean isIpValid(String ip, boolean acceptInnerIp) {
        return acceptInnerIp ? isIpValid(ip) : isIpValidAndNotPrivate(ip);
    }

    /**
     * 仅仅判断ip是否有效
     * @param ip
     * @return
     */
    private static boolean isIpValid(String ip) {
        if (StringUtils.isBlank(ip)) {
            return false;
        }
        String[] split = ip.split("\\.");
        if (split.length != 4) {
            return false;
        }
        try {
            long first = Long.valueOf(split[0]);
            long second = Long.valueOf(split[1]);
            long third = Long.valueOf(split[2]);
            long fourth = Long.valueOf(split[3]);
            return first < 256 && first > 0
                    && second < 256 && second >= 0
                    && third < 256 && third >= 0
                    && fourth < 256 && fourth >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断ip是否有效，并且不是内网ip
     * @param ip
     * @return
     */
    private static boolean isIpValidAndNotPrivate(String ip) {
        if (StringUtils.isBlank(ip)) {
            return false;
        }
        String[] split = ip.split("\\.");
        try {
            long first = Long.valueOf(split[0]);
            long second = Long.valueOf(split[1]);
            long third = Long.valueOf(split[2]);
            long fourth = Long.valueOf(split[3]);
            if (first < 256 && first > 0
                    && second < 256 && second >= 0
                    && third < 256 && third >= 0
                    && fourth < 256 && fourth >= 0) {
                if (first == 10) {
                    return false;
                }
                if (first == 172 && (second >= 16 && second <= 31)) {
                    return false;
                }
                if (first == 192 && second == 168) {
                    return false;
                }
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
