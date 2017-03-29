package com.frame.webapp.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/** 
* @author wowson
* @version create time：2017年3月28日 下午9:38:35 
* 类说明 ：
*/
@Controller
@RequestMapping("/front/acc")
public class AccountController {
	@RequestMapping("/action")
	public Object user(){
		return new ModelAndView("/account/account");
	}
	@RequestMapping("class")
public Object classDy(){
	return new ModelAndView("/account/classDynamic");
}
}
