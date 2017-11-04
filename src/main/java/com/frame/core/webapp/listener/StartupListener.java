package com.frame.core.webapp.listener;

import com.frame.core.utils.SpringWebContextUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.frame.core.components.AjaxResult;
import com.google.gson.Gson;
import org.springframework.web.context.WebApplicationContext;

public class StartupListener implements ApplicationListener<ContextRefreshedEvent>{
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		AjaxResult.setGson(event.getApplicationContext().getBean(Gson.class));
		SpringWebContextUtil.setWebApplicationContext((WebApplicationContext) event.getApplicationContext());
	}

}
