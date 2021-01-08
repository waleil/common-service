package cn.net.yzl.zt.config.redis;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author TimFruit
 * @date 20-1-4 下午6:11
 */
public class MyBeanUtils {

    /**
     * 强制将指定属性properties 从source复制到target
     *  (即使不存在setter, getter方法)
     * @param source
     * @param target
     * @param properties
     */
    public static void forceCopyProperties(Object source, Object target, String... properties){
        if(properties==null || properties.length==0){
            return;
        }

        Class sourceClazz=source.getClass();
        Class targetClazz=target.getClass();


        Field sourceField=null;
        Field targetField=null;

        Object sourceValue=null;

        for(String property: properties){

            sourceField=ReflectionUtils.findField(sourceClazz, property);
            targetField=ReflectionUtils.findField(targetClazz, property);

            ReflectionUtils.makeAccessible(sourceField);
            ReflectionUtils.makeAccessible(targetField);

            sourceValue=ReflectionUtils.getField(sourceField, source);
            ReflectionUtils.setField(targetField, target, sourceValue);
        }

    }


    /**
     * 判断指定属性对应引用是否==
     * @param source
     * @param target
     * @param properties
     */
    public static void assertPropertiesEquals(Object source, Object target, String... properties){
        assertProperties(source, target, (v1, v2)->{ return v1==v2;}, properties);
    }

    /**
     * 判断指定属性对应引用是否 !=
     * @param source
     * @param target
     * @param properties
     */
    public static void assertPropertiesNotEquals(Object source, Object target, String... properties){
        assertProperties(source, target, (v1, v2)->{
            if(v1 ==null && v2==null){
                return true;
            }
            return v1!=v2;
        }, properties);
    }


    /**
     * 判断指定属性为同一类型
     * @param source
     * @param target
     * @param properties
     */
    public static void assertSameTypes(Object source, Object target, String... properties){
        assertProperties(source, target, new AdjustFunction<Object, Object>() {
            @Override
            public boolean adjust(Object v1, Object v2) {
                Class c1=v1.getClass();
                Class c2=v2.getClass();
                boolean result=false;

                Class searchType=c1;
                while(!result && searchType!=Object.class){
                    if(searchType==c2){
                        result=true;
                    }
                    searchType=searchType.getSuperclass();
                }
                if(result){
                    return result;
                }

                searchType=c2;
                while(!result && searchType!=Object.class){
                    if(searchType==c1){
                        result=true;
                    }
                    searchType=searchType.getSuperclass();
                }
                return result;
            }
        }, properties);
    }




    /**
     * 强制将指定属性properties 从source复制到target
     *  (即使不存在setter, getter方法)
     * @param source
     * @param target
     * @param properties
     */
    private static void assertProperties(Object source, Object target,AdjustFunction<Object, Object> adjustFunction, String... properties){
        if(properties==null || properties.length==0){
            return;
        }

        Class sourceClazz=source.getClass();
        Class targetClazz=target.getClass();


        Field sourceField=null;
        Field targetField=null;

        Object sourceValue=null;
        Object targetValue=null;

        for(String property: properties){

            sourceField=ReflectionUtils.findField(sourceClazz, property);
            targetField=ReflectionUtils.findField(targetClazz, property);

            ReflectionUtils.makeAccessible(sourceField);
            ReflectionUtils.makeAccessible(targetField);

            sourceValue=ReflectionUtils.getField(sourceField, source);
            targetValue=ReflectionUtils.getField(targetField, target);

            if(!adjustFunction.adjust(sourceValue, targetValue)){
                throw new RuntimeException(sourceField.getName()+"和"+targetField.getName()+"对应值不符合判断");
            }
        }

    }


    @FunctionalInterface
    private interface AdjustFunction<V1, V2>{
        boolean adjust(V1 v1, V2 v2);
    }







}
