package cn.net.yzl.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Base64;

public class YSecurityUtil {

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

    /** 对字符串进行BASE64编码 */
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

    /** 转换字节数组为十六进制字符串 */
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

    public static String encoder(String str) {
        String result = null;
        try {
            result = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String appId = "yuzhilin15139769986";
        String state = "5c936ffa-ee98-4843-92f4-c65f18542b8c";
        String time = "2020-07-16 11:36:42";
        String appSecretKey ="4083cf22535446d394348f2260e49f38";
        StringBuilder append = new StringBuilder().append(appId).append(state).append(time).append(appSecretKey);
        System.out.println(append);
        System.out.println(encodeByMD5(append.toString().toLowerCase()));
    }
}
