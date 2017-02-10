package com.frame.webapp.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.frame.core.components.AjaxResult;
import com.google.gson.Gson;

public class StartupListener implements ApplicationListener<ContextRefreshedEvent>{
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		AjaxResult.setGson(event.getApplicationContext().getBean(Gson.class));
	}

}
