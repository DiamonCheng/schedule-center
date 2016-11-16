package com.frame.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/"})
public class IndexController {
	Logger LOGGER=LoggerFactory.getLogger(IndexController.class);
	@RequestMapping({"/","/index"})
	public Object index(){
		return "index.html";
	}
	@RequestMapping({"/main"})
	public Object main(){
		return "main.jsp";
	}
	@RequestMapping({"/decorator"})
	public Object decorator(){
		return "decorator/decorator";
	}
	  
}
