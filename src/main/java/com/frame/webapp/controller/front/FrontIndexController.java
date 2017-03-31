package com.frame.webapp.controller.front;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.frame.core.components.UserAuthoritySubject;
import com.frame.core.entity.UserEntity;
import com.frame.entity.media.IndexShowEntity;
import com.frame.service.front.FrontIndexService;

/** 
* @author wowson
* @version create time：2017年3月13日 上午10:55:34 
* 类说明 ：
*/
@Controller
public class FrontIndexController {
	/*@RequestMapping({"/"})
	public Object index(){
		return "redirect:/front/index";
	}*/
	@Autowired
	FrontIndexService service;
	@RequestMapping({"/front/index"})
	public Object index(HttpServletRequest request){
		IndexShowEntity entity= service.get();
		return new ModelAndView("index/index").addObject("article",entity);
	}
	@RequestMapping({"/indexDecorator"})
	public Object indexDecorator(HttpServletRequest request){
		UserEntity user = (UserEntity) UserAuthoritySubject.getAccountSubject();
		return new ModelAndView("index/index-decorator").addObject("user",user);
		
	}
}
