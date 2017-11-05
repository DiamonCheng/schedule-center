package com.frame.core.webapp.controller.account;

import com.frame.core.components.UserAuthoritySubject;
import com.frame.core.service.account.UserService;
import com.frame.core.webapp.controller.BaseController;
import com.frame.core.webapp.interceptor.GeneralIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/"})
public class LoginController extends BaseController{
	@Autowired
	private UserService userService;
	@RequestMapping("/login")
	public Object login(String userLoginVerification,String userPassword){
		if(userService.login(userLoginVerification, userPassword)==0){
			String redirectFor;
			if ((redirectFor= (String) UserAuthoritySubject.getSession().getAttribute(GeneralIntercepter.REQUEST_URI_BEFORE_LOGIN_THREAD_KEY))!=null){
				UserAuthoritySubject.getSession().removeAttribute(GeneralIntercepter.REQUEST_URI_BEFORE_LOGIN_THREAD_KEY);
				return "redirect:"+redirectFor;
			}
			return "redirect:/main";
		}else{
			return new ModelAndView("account/login").addObject("userLoginVerification", userLoginVerification).addObject("message", userLoginVerification==null?"":"1");
		}
	}
}
