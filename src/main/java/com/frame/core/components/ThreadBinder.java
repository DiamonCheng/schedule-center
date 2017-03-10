package com.frame.core.components;

import java.util.HashMap;
import java.util.Map;
/**
 * 线程绑定对象，在web应用中相当于全局变量。
 * @author Defferson.Cheng
 *
 */
public enum ThreadBinder {
	REQUEST,RESPONSE,SESSION;
	private static ThreadLocal<Map<Object,Object>> THREAD_LOCAL=new ThreadLocal<Map<Object,Object>>(){
		protected java.util.Map<Object,Object> initialValue() {
			return new HashMap<Object,Object>();
		}
	};
	public static void set(Object key,Object value) {
		THREAD_LOCAL.get().put(key, value);
	}
	@SuppressWarnings("unchecked")
	public static <T> T get(Object key){
		return (T)THREAD_LOCAL.get().get(key);
	}
}
