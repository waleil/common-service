package cn.net.yzl.operatelogger.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName SpringContextUtil
 * @Descripion SpringContext环境类
 * @Author liwenjie
 * @Date 2020/12/10 9:57
 **/
@Component
public class SpringContextUtil implements ApplicationContextAware{

	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if(cn.net.yzl.operatelogger.common.SpringContextUtil.applicationContext == null){
			cn.net.yzl.operatelogger.common.SpringContextUtil.applicationContext = applicationContext;
		}

	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}


	public static Object getBean(String name){
		return getApplicationContext().getBean(name);
	}

	public static <T> T getBean(Class<T> clazz){
		return getApplicationContext().getBean(clazz);
	}

}
