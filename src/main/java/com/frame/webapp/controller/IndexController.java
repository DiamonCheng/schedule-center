package com.frame.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.frame.core.components.UserAuthoritySubject;
import com.frame.core.utils.HttpContextUtil;
import org.sitemesh.webapp.contentfilter.HttpServletRequestFilterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.frame.core.components.NavigationOption;
import com.frame.core.components.ThreadBinder;
import com.frame.entity.MenuEntity;
import com.frame.service.AuthorityService;
import com.frame.webapp.interceptor.GeneralIntercepter;

@Controller
@RequestMapping({"/"})
public class IndexController extends BaseController{
	@RequestMapping({"/","/index"})
	public Object main(){
		return "redirect:/main";
	}
	@RequestMapping({"/main"})
	public Object main(HttpServletRequest request){
		request.setAttribute("a", "123");
		return "main.jsp";
	}
	@Autowired
	private AuthorityService authorityService;
	@SuppressWarnings("unchecked")
	@RequestMapping({"/decorator"})
	public Object decorator(HttpServletRequest request){
		String requestURI= (String) request.getAttribute(GeneralIntercepter.REQUEST_URI_REQUEST_KEY);
//		UserAuthoritySubject.getSession().setAttribute(GeneralIntercepter.REQUEST_URI_SESSION_KEY, null);
		List<MenuEntity> menuLocation=authorityService.getMenuLocation(requestURI);
		List<MenuEntity> menuList=authorityService.getMenuList();
		List<NavigationOption> options= (List<NavigationOption>) request.getAttribute(AuthorityService.NAVIGATION_OPTIONS_KEY);
		return new ModelAndView("decorator/decorator")
				.addObject("navigation", menuLocation)
				.addObject("options", options)
				.addObject("menuList", menuList)
				.addObject("currentLocation", menuLocation.size()>0?menuLocation.get(menuLocation.size()-1):new MenuEntity());
	}
	  
}
