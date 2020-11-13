package cn.net.yzl.common.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.json.XML;
import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * xml 的工具类
 */
public class XMLUtil {


    /**
     * XML格式字符串转换为Map
     *
     * @param xmlStr XML字符串
     * @return XML数据转换后的Map
     * @since 4.0.8
     */
    public static Map<String, Object> xmlToMap(String xmlStr) {

        return XmlUtil.xmlToMap(xmlStr, new HashMap<>());
    }

    /**
     * 将Map转换为XML格式的字符串
     *
     * @param data Map类型数据
     * @return XML格式的字符串
     * @since 5.1.2
     */
    public static String mapToXmlStr(Map<?, ?> data) {

        return XmlUtil.toStr(XmlUtil.mapToXml(data, "xml"));
    }

    public static <T> T xmlStrToBean(String xmlStr, Class<T> bean) {
        Map<String, Object> map = XmlUtil.xmlToMap(xmlStr, new HashMap<>());
        return BeanUtil.toBean(map, bean);
    }

}
