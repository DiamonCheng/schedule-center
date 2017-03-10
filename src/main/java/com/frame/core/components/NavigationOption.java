package com.frame.core.components;

import java.io.Serializable;

public class NavigationOption implements Serializable{
	private static final long serialVersionUID = -2860245224761084736L;
	private String name;
	private String callback;
	public NavigationOption(){}
	public NavigationOption(String name, String callback) {
		super();
		this.name = name;
		this.callback = callback;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
}
