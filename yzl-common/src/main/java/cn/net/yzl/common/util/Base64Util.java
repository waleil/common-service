package cn.net.yzl.common.util;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;

import java.io.File;
import java.io.InputStream;

/**
 * base 64 工具类
 */
public class Base64Util {


    /**
     * 编码
     *
     * @param source 被编码的base64字符串
     * @return 加密后的字符串
     */
    public static String encode(byte[] source) {

        return Base64Encoder.encode(source);
    }

    /**
     * base64编码
     *
     * @param in 被编码base64的流（一般为图片流或者文件流）
     * @return 被加密后的字符串
     */
    public static String encode(InputStream in) {

        return Base64Encoder.encode(IoUtil.readBytes(in));
    }

    /**
     * base64解码
     *
     * @param source 被解码的base64字符串
     * @return 被加密后的字符串
     */
    public static String decodeStr(CharSequence source) {
        return Base64Decoder.decodeStr(source);
    }

    /**
     * base64解码
     *
     * @param base64   被解码的base64字符串
     * @param destFile 目标文件
     * @return 目标文件
     */
    public static File decodeToFile(CharSequence base64, File destFile) {
        return FileUtil.writeBytes(Base64Decoder.decode(base64), destFile);
    }

    /**
     * base64解码
     *
     * @param base64 被解码的base64字符串
     * @return 被加密后的字符串
     */
    public static byte[] decode(CharSequence base64) {

        return Base64Decoder.decode(base64);
    }
    /**
     * 解码Base64
     *
     * @param in 输入
     * @return 解码后的bytes
     */
    public static byte[] decode(byte[] in) {
        return Base64Decoder.decode(in);
    }
}
