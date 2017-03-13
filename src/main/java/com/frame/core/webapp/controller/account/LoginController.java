package com.frame.core.webapp.controller.account;

import com.frame.core.components.AjaxResult;
import com.frame.core.components.UserAuthoritySubject;
import com.frame.core.entity.UserEntity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.frame.core.service.account.UserService;
import com.frame.core.webapp.controller.BaseController;
import com.frame.core.webapp.interceptor.GeneralIntercepter;

@Controller
@RequestMapping({"/"})
public class LoginController extends BaseController{
	@Autowired
	private UserService userService;
	@RequestMapping("/login")
	public Object login(String userLoginVerification,String userPassword){
		if(userService.login(userLoginVerification, userPassword)==0){
			String redirectFor;
			UserEntity user = (UserEntity)UserAuthoritySubject.getAccountSubject();
			if ((redirectFor= (String) UserAuthoritySubject.getSession().getAttribute(GeneralIntercepter.REQUEST_URI_BEFORE_LOGIN_THREAD_KEY))!=null){
				UserAuthoritySubject.getSession().removeAttribute(GeneralIntercepter.REQUEST_URI_BEFORE_LOGIN_THREAD_KEY);
				return "redirect:"+redirectFor;
			}
			//TODO用户和管理员分流
			if(user.getInitId() == 1)
				return "redirect:/admin/main";
			else 
				return "redirect:/front/index";
		}else{
			return new ModelAndView("account/login").addObject("userLoginVerification", userLoginVerification).addObject("message", userLoginVerification==null?"":"1");
		}
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public Object registerCtr(String name, String pwd,String checkPwd) throws Exception {
		Map<String,Object> result = new HashMap<>();
		AjaxResult ajaxResult = new AjaxResult();
		if (StringUtils.isEmpty(name) && StringUtils.isEmpty(pwd)){
			return new RuntimeException("用户名或密码为空");
		}
		UserEntity user = new UserEntity();
		user.setUserPassword(pwd);
		user.setUserLoginVerification(name);
		try {
			userService.registerUser(user);
			
		} catch (Exception e) {
			LOGGER.debug("注册失败");
			return new RuntimeException("用户名已存在！",e);
		}
		ajaxResult.setData(result.put("message", "2"));
		return ajaxResult;
	}
}
