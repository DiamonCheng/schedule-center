package com.frame.core.utils;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * 用于获取Spring的Bean
 * 注意，在服务没有初始化完成之前使用会抛异常。
 * @author Defferson.Cheng
 * 2016年11月3日15:25:57
 */
public class SpringWebContextUtil {
	private static WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
	public static WebApplicationContext getWebApplicationContext() {
		return webApplicationContext;
	}
	public static <T> T getBean(Class<T> cls){
		return webApplicationContext.getBean(cls);
	}
	public static Object getBean(String id){
		return webApplicationContext.getBean(id);
	}
}
