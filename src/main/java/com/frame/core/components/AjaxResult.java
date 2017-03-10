package com.frame.core.components;

import java.io.Serializable;

import com.google.gson.Gson;

public class AjaxResult implements Serializable{
	private static final long serialVersionUID = -6770538785772264261L;
	private static Gson gson;
	public static Gson getGson() {
		return gson;
	}
	public static void setGson(Gson gson) {
		AjaxResult.gson = gson;
	}
	private String code="00";
	private String message="成功";
	private Object data;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return gson.toJson(this);
	}
}
