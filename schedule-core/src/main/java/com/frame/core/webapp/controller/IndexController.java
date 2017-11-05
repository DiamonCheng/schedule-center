package com.frame.core.webapp.controller;

import com.frame.core.components.NavigationOption;
import com.frame.core.components.UserAuthoritySubject;
import com.frame.core.entity.MenuEntity;
import com.frame.core.entity.UserEntity;
import com.frame.core.service.account.AuthorityService;
import com.frame.core.webapp.interceptor.GeneralIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
		return "main";
	}
	@Autowired
	private AuthorityService authorityService;
	@SuppressWarnings("unchecked")
	@RequestMapping({"/decorator"})
	public Object decorator(HttpServletRequest request){
		String requestURI= (String) request.getAttribute(GeneralIntercepter.REQUEST_URI_REQUEST_KEY);
		if (requestURI==null){
			LOGGER.warn("requestURI is null. Please check the request header accept:"+request.getHeader("accept"));
			return null;
		}
//		UserAuthoritySubject.getSession().setAttribute(GeneralIntercepter.REQUEST_URI_SESSION_KEY, null);
		List<MenuEntity> menuLocation=authorityService.getMenuLocation(requestURI);
		List<MenuEntity> menuList=authorityService.getMenuListWithRole(UserAuthoritySubject.<UserEntity>getAccountSubject());
		List<NavigationOption> options= (List<NavigationOption>) request.getAttribute(AuthorityService.NAVIGATION_OPTIONS_KEY);
		return new ModelAndView("decorator/decorator")
				.addObject("navigation", menuLocation)
				.addObject("options", options)
				.addObject("menuList", menuList)
				.addObject("currentLocation", menuLocation.size()>0?menuLocation.get(menuLocation.size()-1):new MenuEntity(){{setId(-1L);}});
	}
	  
}
