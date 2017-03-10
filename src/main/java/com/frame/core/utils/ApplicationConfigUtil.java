package com.frame.core.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ApplicationConfigUtil {
	public static class PropertiesLoadExeption extends RuntimeException{
		private static final long serialVersionUID = -2053290696718624157L;
		public PropertiesLoadExeption(String message,Throwable t) {
			super(message,t);
		}
	}
	private static final Map<String,Properties> PROPERTIES=new HashMap<String,Properties>();
	public static Properties load(String name,String path,Class<?> loader){
		if (loader==null) loader=Class.class;
		Properties p=new Properties();
		try {
			p.load(loader.getResourceAsStream(path));
		} catch (IOException e) {
			throw new PropertiesLoadExeption("Fialed to load properties "+path, e);
		}
		PROPERTIES.put(name, p);
		return p;
	}
	public static String get(String name,String key){
		Properties p=PROPERTIES.get(name);
		if (p==null) return null;
		return p.getProperty(key);
	}
	public static Properties get(String name){
		return PROPERTIES.get(name);
	}
}
