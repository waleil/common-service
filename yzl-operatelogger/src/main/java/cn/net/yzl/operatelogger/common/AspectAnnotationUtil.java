package cn.net.yzl.operatelogger.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @ClassName AspectAnnotationUtil
 * @Descripion 切面注解工具类
 * @Author liwenjie
 * @Date 2020/12/10 9:57
 **/
public class AspectAnnotationUtil {

    /**
     * 根据注解Class对象获取方法上的注解
     * @param annotationType
     * @param joinPoint
     * @param <A>
     * @return 该注解
     */
    public static  <A extends Annotation> A getAnnotation(Class<A> annotationType, JoinPoint joinPoint){

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(annotationType);
        }
        return null;
    }
}
