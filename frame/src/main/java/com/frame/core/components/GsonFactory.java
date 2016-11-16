package com.frame.core.components;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GsonFactory {
	private static SimpleDateFormat dateFormart1=new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat dateFormart2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat dateFormart3=new SimpleDateFormat("yyyy/MM/dd");
	private static SimpleDateFormat dateFormart4=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public static Gson buildDefaultGson(){
		return new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			
			@Override
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				JsonParseException e=null;
				try {
					return dateFormart2.parse(json.getAsString());
				} catch (Exception ex) {
					e=new JsonParseException("日期类型 JSON："+json+"无法转化");
					e.addSuppressed(ex);
				}
				try {
					return dateFormart1.parse(json.getAsString());
				} catch (Exception ex) {
					e=new JsonParseException("日期类型 JSON："+json+"无法转化");
					e.addSuppressed(ex);
				}
				try {
					return dateFormart4.parse(json.getAsString());
				} catch (Exception ex) {
					e=new JsonParseException("日期类型 JSON："+json+"无法转化");
					e.addSuppressed(ex);
				}
				try {
					return dateFormart3.parse(json.getAsString());
				} catch (Exception ex) {
					e=new JsonParseException("日期类型 JSON："+json+"无法转化");
					e.addSuppressed(ex);
				}
				try {
					return new Date(json.getAsLong());
				} catch (Exception ex) {
					e=new JsonParseException("日期类型 JSON："+json+"无法转化");
					e.addSuppressed(ex);
				}
				throw e;
			}
		}).registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
			@Override
			public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
				return new JsonPrimitive(dateFormart2.format(src));
			}
		}).create();
	}
	public static SimpleDateFormat getDateFormart1() {
		return dateFormart1;
	}
	public static SimpleDateFormat getDateFormart2() {
		return dateFormart2;
	}
}
