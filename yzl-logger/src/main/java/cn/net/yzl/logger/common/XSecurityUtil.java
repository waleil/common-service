package cn.net.yzl.logger.common;

import java.security.MessageDigest;
import java.util.Base64;

/**
 * @title: XSecurityUtil
 * @Author lichanghong
 * @Date: 2020/11/30 9:48 上午
 * @Version 1.0
 */
public class XSecurityUtil {
    /**
     * 转换字节数组为十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(byteToHexString(b));
        }
        return sb.toString();
    }

    /** 将一个字节转化成十六进制形式的字符串 */
    private static String byteToHexString(byte b) {
        String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /** 对字符串进行MD5加密 */
    public static String encodeByMD5(String src) {
        if (src != null) {
            try {
                // 创建具有指定算法名称的信息摘要
                MessageDigest md = MessageDigest.getInstance("MD5");
                // 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算, 将得到的字节数组变成字符串返回
                byte[] bytes = md.digest(src.getBytes());
                return byteArrayToHexString(bytes).toUpperCase();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        } else {
            return null;
        }
    }

    public static String encodeByBASE64(String originString) {
        if (originString != null) {
            try {
                return Base64.getEncoder().encodeToString(originString.getBytes());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
