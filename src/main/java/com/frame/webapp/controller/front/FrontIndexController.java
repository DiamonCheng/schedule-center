package com.frame.webapp.controller.front;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	@RequestMapping({"/front/index"})
	public Object index(HttpServletRequest request){
		return "index/index.jsp";
	}
	@RequestMapping({"/indexDecorator"})
	public Object indexDecorator(HttpServletRequest request){
		return new ModelAndView("index/index-decorator");
		
	}
}
