package cn.net.yzl.logger.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @title: XCollectionUtil
 * @Author lichanghong
 * @Date: 2020/11/30 9:46 上午
 * @Version 1.0
 */
public class XCollectionUtil {
    public static boolean isAnyEmpty(Object... args) {
        for (Object o : args) {
            if (null == o
                    || (o instanceof CharSequence && StringUtils.isBlank((CharSequence) o))
                    || (o instanceof Collection && ((Collection) o).isEmpty())
            ) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAnyNotEmpty(Object... args) {
        for (Object o : args) {
            if (null != o) {
                if ((o instanceof CharSequence && StringUtils.isNotBlank((CharSequence) o))) {
                    return true;
                } else if ((o instanceof Collection && !(((Collection) o).isEmpty()))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isAnyNull(Object... args) {
        for (Object o : args) {
            if (null == o) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNoneEmpty(Object... args) {
        return !isAnyEmpty(args);
    }

    public static <T, R> R evalFirstIfExists(List<T> list, Function<T, R> func) {
        if (!CollectionUtils.isEmpty(list)) {
            T t = list.get(0);
            if (null != t && null != func) {
                return func.apply(t);
            }
        }
        return null;
    }

    public static <T, R> List<R> evalIfExists(List<T> list, Function<T, R> func) {
        List<R> rs = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            list.stream().filter(Objects::nonNull).forEach(t -> {
                R r = func.apply(t);
                if (null != r) { rs.add(r); }
            });
        }
        return rs;
    }

    public static boolean likeAny(String str, Collection<String> coll) {
        if (null == str || null == coll) { return false; }
        for (String c : coll) {
            if (str.contains(c)) { return true; }
        }
        return false;
    }

    public static boolean likeAny(String str, String[] arr) {
        if (null == str || null == arr) { return false; }
        for (int i = 0; i < arr.length; i++) {
            String e = arr[i].trim();
            if (StringUtils.isBlank(e)) { continue; }
            if (str.contains(e)) { return true; }
        }
        return false;
    }

    public static  <T> Map<String , List<T>> computeSet(Collection<T> left, Collection<T> right){
        List<T> diffInLeft = Lists.newArrayList();
        List<T> diffInRight = Lists.newArrayList();
        List<T> intersection = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(left) && !CollectionUtils.isEmpty(right)){
            diffInRight = right.stream()
                    .filter(f -> !left.contains(f))
                    .collect(Collectors.toList());
            diffInLeft  = left.stream()
                    .filter(f -> !right.contains(f))
                    .collect(Collectors.toList());
            intersection  = left.stream()
                    .filter(f -> right.contains(f))
                    .collect(Collectors.toList());
        }else if (CollectionUtils.isEmpty(right)){
            diffInLeft = left.stream().collect(Collectors.toList());
        }else {
            diffInRight = right.stream().collect(Collectors.toList());
        }

        Map<String , List<T>> computeRes = Maps.newHashMap();
        computeRes.put("diffInLeft" , diffInLeft);
        computeRes.put("diffInRight" , diffInRight);
        computeRes.put("intersection" , intersection);

        return computeRes;
    }
}
