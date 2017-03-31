package com.frame.webapp.controller.front;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.frame.core.components.AjaxResult;
import com.frame.core.components.UserAuthoritySubject;
import com.frame.core.entity.UserEntity;
import com.frame.core.utils.EncriptUtil;
import com.frame.model.dto.media.ClassDynamicDTO;
import com.frame.service.front.FrontClassDynamicService;
import com.frame.service.front.FrontUserService;

/**
 * @author wowson
 * @version create time：2017年3月28日 下午9:38:35 类说明 ：
 */
@Controller
@RequestMapping("/front/acc")
public class AccountController {
	@Autowired
	FrontClassDynamicService service;
	@RequestMapping("/action")
	public Object user() {
		UserEntity entity = UserAuthoritySubject.getAccountSubject();
		return new ModelAndView("/account/account").addObject("user", entity);
	}
	@Autowired
	FrontUserService uesrService;
	@RequestMapping("update")
	@ResponseBody
	public Object update(String nickName,String userPassword){
		AjaxResult result = new AjaxResult();
		if(StringUtils.isEmpty(userPassword) ||StringUtils.isEmpty(nickName)){
			result.setCode("-1");
			result.setMessage("请将信息填完整！");
			return result;
		}
		UserEntity user = UserAuthoritySubject.getAccountSubject();
		user.setNickName(nickName);
		user.setUpdateDateTime(new Date());
		user.setUserPassword(EncriptUtil.encriptSHA1(userPassword));
		uesrService.update(user);
		result.setMessage("修改成功");
		return result;
	}
	@RequestMapping("/class")
	public Object classDy(ClassDynamicDTO dto) {
		service.select(dto);
		return new ModelAndView("/account/classDynamic").addObject("page", dto);
	}
	@RequestMapping("/detail")
	public  Object detail(Long id){
		return new ModelAndView("/account/classDynamicDetail").addObject("detail", service.get(id));
	}
}
