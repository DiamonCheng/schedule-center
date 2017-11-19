package com.frame.core.components;

import com.google.gson.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GsonFactory {
	private static GsonFactory instance;
	public GsonFactory(){
		if (instance==null) instance=this;
	}
	
	private List<String> dateFomartPatternList=new ArrayList<>(Arrays.asList("yyyy-MM-dd","yyyy-MM-dd HH:mm:ss","yyyy/MM/dd","yyyy/MM/dd HH:mm:ss"));
	private String defaultOutputDateFomart="yyyy-MM-dd HH:mm:ss";
	
	public static Gson buildDefaultGson(){
	    if (instance==null) new GsonFactory();
	    return instance.buildGson();
    }
	
	public Gson buildGson(){
		return new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			
			@Override
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				JsonParseException e=new JsonParseException("Date type JSON："+json+", can not parse!");
				for (String pattern:dateFomartPatternList)  try{
					return new SimpleDateFormat(pattern).parse(json.getAsString());
				}catch (Exception ex) {
					e.addSuppressed(ex);
				}
				try {
					return new Date(json.getAsLong());
				} catch (Exception ex) {
					e.addSuppressed(ex);
				}
				throw e;
			}
		}).registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
			@Override
			public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
				return new JsonPrimitive(new SimpleDateFormat(defaultOutputDateFomart).format(src));
			}
		}).registerTypeAdapter(Class.class, new JsonSerializer<Class<?>>() {
			@Override
			public JsonElement serialize(Class<?> src, Type typeOfSrc, JsonSerializationContext context) {
				return new JsonPrimitive(src.getName());
			}
		}).registerTypeAdapter(Class.class, new JsonDeserializer<Class<?>>() {

			@Override
			public Class<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				try {
					return Class.forName(json.getAsString());
				} catch (ClassNotFoundException e) {
					throw new JsonParseException(e);
				}
			}
		}).registerTypeAdapter(Throwable.class, new JsonSerializer<Throwable>() {
			@Override
			public JsonElement serialize(Throwable src, Type typeOfSrc, JsonSerializationContext context) {
				StringWriter sw=new StringWriter();
				PrintWriter pw=new PrintWriter(sw);
				src.printStackTrace(pw);
				pw.close();
				return new JsonPrimitive(sw.toString());
			}
		}).create();
	}
    
    public static GsonFactory getInstance() {
        return instance;
    }
    
    public List<String> getDateFomartPatternList() {
        return dateFomartPatternList;
    }
    
    public String getDefaultOutputDateFomart() {
        return defaultOutputDateFomart;
    }
    
    public GsonFactory setDateFomartPatternList(List<String> dateFomartPatternList) {
        this.dateFomartPatternList = dateFomartPatternList;
        return this;
    }
    
    public GsonFactory setDefaultOutputDateFomart(String defaultOutputDateFomart) {
        this.defaultOutputDateFomart = defaultOutputDateFomart;
        return this;
    }
}
