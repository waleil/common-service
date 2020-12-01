package cn.net.yzl.logger.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @title: XContextUtil
 * @Author lichanghong
 * @Date: 2020/11/30 9:31 上午
 * @Version 1.0
 */
public class XContextUtil {
    private static ThreadLocal<Map<String, Object>> map = new InheritableThreadLocal<>();

    @SuppressWarnings("unchecked")
    public static <T> T getValue(String key) {
        Map<String, Object> m = map.get();
        if (null == m) { init(); }
        return (T) map.get().get(key);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getValue(String key, T defaultValue) {
        Map<String, Object> m = map.get();
        if (null == m) { init(); }
        Object o = map.get().get(key);
        return null == o ? defaultValue : (T) o;
    }

    @SuppressWarnings("unchecked")
    public static <T> T setValue(String key, Object value) {
        if (null == map.get()) { init(); }
        return (T) map.get().put(key, value);
    }

    public static void clear() {
        map.get().clear();
    }

    public static void clear(Object key) {
        if (null != map.get() && null != map.get().get(key)){
            map.get().remove(key);
        }
    }


    public static void init() { map.set(new HashMap<>()); }

    public static <S, T> T eval(Map<String, Object> keys, boolean force, Function<S, T> function) {
        Map<String, Object> oldKeys = new HashMap<>();
        for (String k : keys.keySet()) {
            Object oldV = getValue(k, null);
            oldKeys.put(k, oldV);
            if (force || null == oldV) {
                setValue(k, keys.get(k));
            }
        }
        T t = function.apply(null);
        for (String k : oldKeys.keySet()) {
            setValue(k, oldKeys.get(k));
        }
        return t;
    }

    public static <S, T> T eval(String k, Object v, Function<S, T> function) {
        return eval(Collections.singletonMap(k, v), true, function);
    }

    public static <S, T> T eval(String k, Object v, boolean force, Function<S, T> function) {
        return eval(Collections.singletonMap(k, v), force, function);
    }
}
