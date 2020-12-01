package cn.net.yzl.logger.common;

import com.google.common.collect.Maps;
import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @title: XMapUtil
 * @Author lichanghong
 * @Date: 2020/11/30 9:44 上午
 * @Version 1.0
 */
public class XMapUtil {
    public static <T> Map<String, T> map(String[] keys, T[] values) {
        Map<String, T> m = new HashMap<>();
        for(int i = 0; null != keys && i < keys.length; i++) {
            m.put(keys[i], values[i]);
        }
        return m;
    }

    public static <T> Map<String, T> mapOne(String key, T value) {
        Map<String, T> m = new HashMap<>();
        m.put(key, value);
        return m;
    }

    public static <T> Map<String, T> mapTwo(String key1, T value1, String key2, T value2) {
        Map<String, T> m = new HashMap<>();
        m.put(key1, value1);
        m.put(key2, value2);
        return m;
    }

    public static <T> Map<String, T> mapThree(String key1, T value1, String key2, T value2, String key3, T value3) {
        Map<String, T> m = new HashMap<>();
        m.put(key1, value1);
        m.put(key2, value2);
        m.put(key3, value3);
        return m;
    }

    /**
     * 将对象装换为map
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                if (Objects.nonNull(beanMap.get(key))){
                    map.put(key+"" , beanMap.get(key));
                }
            }
        }
        return map;
    }
}
