package cn.net.yzl.common.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;

import java.nio.charset.Charset;

/**
 * MD5 的工具类
 *
 */
public class MD5util {


    /**
     * 计算32位MD5摘要值，并转为16进制字符串
     *
     * @param data    被摘要数据
     * @param salt 盐值
     * @param charset 编码
     * @return MD5摘要的16进制表示
     */
    public static String md5Hex(String data,String salt, String charset) {

        return new MD5(salt.getBytes()).digestHex(data, charset);
    }
    /**
     * 计算32位MD5摘要值，并转为16进制字符串
     * @param salt 盐值
     * @param data 被摘要数据
     * @return MD5摘要的16进制表示
     */
    public static String md5Hex(String data,String salt) {

        return md5Hex(data,salt, CharsetUtil.UTF_8);
    }

    /**
     * 计算16位MD5摘要值，并转为16进制字符串
     *
     * @param data    被摘要数据
     * @param salt 盐值
     * @param charset 编码
     * @return MD5摘要的16进制表示
     */
    public static String md5Hex16(String data,String salt, Charset charset) {

        return new MD5(salt.getBytes()).digestHex16(data, charset);
    }

    /**
     * 计算16位MD5摘要值，并转为16进制字符串
     *
     * @param data 被摘要数据
     * @param salt 盐值
     * @return MD5摘要的16进制表示
     */
    public static String md5Hex16(String data,String salt) {

        return md5Hex16(data,salt, CharsetUtil.CHARSET_UTF_8);
    }


    public static void main(String[] args) {
        String abc = md5Hex16("abc", "123");
        System.err.println(abc);
    }
}
