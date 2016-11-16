package com.frame.core.components;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/**
 * 自定义视图解析(通过配置实现多视图整合,如jsp,velocity,freemarker,pdf,excel...)
 * @author Defferson.Cheng
 */
public class GeneralViewResolver implements ViewResolver  {
    
    private static Log LOGGER = LogFactory.getLog(GeneralViewResolver.class);

    public View resolveViewName(String viewName, Locale locale) throws Exception {
    	String suffix=viewNameSuffix(viewName);
    	if (suffix==null)
    		if (defaultViewResolver!=null){
    			LOGGER.debug(" View with name '"+viewName+"' use default viewResolver "+defaultViewResolver);
    			return defaultViewResolver.resolveViewName(viewName, locale);
    		}else{
    			LOGGER.warn("ViewResolver Not Found, view: '"+viewName);
    			return null;
    		}
    	ViewResolver vr=viewResolverMap.get(suffix);
    	if (vr!=null) {
    		LOGGER.debug(" View with name '"+viewName+"' use viewResolver "+vr);
    		return vr.resolveViewName(viewName, locale);
    	}
    	LOGGER.warn("ViewResolver Not Found, view: '"+viewName);
    	return null;
    }
    private String viewNameSuffix(String viewName){
    	int index=viewName.lastIndexOf('.');
    	if (-1==index) return null;
    	String suffix=viewName.substring(index, viewName.length());
    	return suffix;
    }
    
    private Map<String,ViewResolver> viewResolverMap = new HashMap<String,ViewResolver>();
    
    private ViewResolver defaultViewResolver = null;

    public ViewResolver getDefaultViewResolver() {
        return defaultViewResolver;
    }

    public void setDefaultViewResolver(ViewResolver defaultViewResolver) {
        this.defaultViewResolver = defaultViewResolver;
    }

	public Map<String,ViewResolver> getViewResolverMap() {
		return viewResolverMap;
	}

	public void setViewResolverMap(Map<String,ViewResolver> viewResolverMap) {
		this.viewResolverMap = viewResolverMap;
	}
}