package cn.net.yzl.logger.common;

import cn.net.yzl.common.enums.ResponseCodeEnums;
import cn.net.yzl.logger.Log;
import cn.net.yzl.logger.json.JacksonUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @title: XBeanUtil
 * @Author lichanghong
 * @Date: 2020/11/30 9:51 上午
 * @Version 1.0
 */
@Slf4j
public class XBeanUtil {
    private final static String BASEPACKAGE = "cn.net.yzl";

    public static <T> T map2Object(Map<String, Object> map, Class<T> clazz) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (map == null) {
            return null;
        }
        T obj = null;
        try {
            // 使用newInstance来创建对象
            obj = clazz.newInstance();
            // 获取类中的所有字段
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                // 判断是拥有某个修饰符
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                // 当字段使用private修饰时，需要加上
                field.setAccessible(true);
                // 获取参数类型名字
                Class<?> filedType = field.getType();
                String filedTypeName = filedType.getName();

                // 判断是否为时间类型，使用equalsIgnoreCase比较字符串，不区分大小写
                // 给obj的属性赋值
                if (filedTypeName.equalsIgnoreCase("java.util.date")) {
                    String datetimestamp = (String) map.get(field.getName());
                    if (StringUtils.isEmpty(datetimestamp) || datetimestamp.equalsIgnoreCase("null")) {
                        field.set(obj, null);
                    } else {
                        field.set(obj, sdf.parse(datetimestamp));
                    }
                } else if (filedTypeName.equalsIgnoreCase("java.util.List")){
                    // 当前集合的泛型类型
                    Type genericType = field.getGenericType();
                    if (null == genericType) {
                        continue;
                    }
                    if (genericType instanceof ParameterizedType) {
                        ParameterizedType pt = (ParameterizedType) genericType;
                        // 得到泛型里的class类型对象
                        Class<?> actualTypeArgument = (Class<?>)pt.getActualTypeArguments()[0];
//            List<Object> curEleList = new ArrayList<>();
//            Object actualType = actualTypeArgument.newInstance();
//            //....actualType字段处理
//            curEleList.add(actualType);
                        if (actualTypeArgument.getTypeName().startsWith(BASEPACKAGE)){
                            List<Object> fieldList = Lists.newArrayList();
                            ((List)map.get(field.getName())).stream().forEach(f->{
                                fieldList.add(XBeanUtil.map2Object((Map<String, Object>)f , actualTypeArgument));
                            });
                            field.set(obj , fieldList);
                        }else {
                            field.set(obj, map.get(field.getName()));
                        }
                    }
                }else if (filedTypeName.startsWith(BASEPACKAGE)){
                    field.set(obj, XBeanUtil.map2Object((Map<String, Object>)map.get(field.getName()),filedType));
                }else {
                    field.set(obj, map.get(field.getName()));
                }
            }
        } catch (Exception e) {
            log.error(ResponseCodeEnums.BEAN_OPT_ERROR.getMessage() + " logKey:{} | traceId:{}| code: {} | message: {}| param: {}",
                    "XBeanUtil.map2Object" , Log.getTraceId() , ResponseCodeEnums.BEAN_OPT_ERROR.getMessage(),
                    ResponseCodeEnums.BEAN_OPT_ERROR.getCode() , "map: "+ JacksonUtil.toJsonString(map)+"class:"+clazz.toString(), e);
        }
        return obj;
    }

    @SuppressWarnings({"unchecked"})
    public static <T> T from(T tar, Object src, boolean replaceIfPresent) {
        if (null == tar || null == src) { return null; }
        Field fields[] = tar.getClass().getDeclaredFields();
        for (Field f : fields) {
            try {
                Object v2 = null;
                for (Field f2 : src.getClass().getDeclaredFields()) {
                    if (Objects.equals(f2.getName(), f.getName())) {
                        f2.setAccessible(true);
                        v2 = f2.get(src);
                        break;
                    }
                }
                f.setAccessible(true);
                if (null == f.get(tar) || replaceIfPresent) {
                    f.set(tar, v2);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return tar;
    }

    @SuppressWarnings({"unchecked"})
    public static <T> T from(Class<T> tarClass, Object src){
        T tar = null;
        try {
            tar = tarClass.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return from(tar, src, true);
    }

    public static <T> T extractField(Object src, String fieldName) {
        if (null == src) { return null; }
        try {
            Field f = src.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            return (T) f.get(src);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return null;
    }

    public static <T> T invokeMethod(Object src, String methodName, Object[] args, Class<?>[] parameterTypes) {
        if (null == src) { return null; }
        try {
            Method m = src.getClass().getDeclaredMethod(methodName, parameterTypes);
            m.setAccessible(true);
            return (T) m.invoke(src, args);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return null;
    }

    public static boolean isBaseType(Class clazz) {
        if (null == clazz) { return false; }
        return clazz.equals(Integer.class) ||
                clazz.equals(String.class) ||
                clazz.equals(Byte.class) ||
                clazz.equals(Long.class) ||
                clazz.equals(Double.class) ||
                clazz.equals(Float.class) ||
                clazz.equals(Character.class) ||
                clazz.equals(Short.class) ||
                clazz.equals(Date.class) ||
                clazz.equals(Boolean.class);
    }

    public static boolean isType(Class<?> src, Class<?> target) {
        return Objects.equals(src, target)
                || src.isAssignableFrom(target);
    }
}
