package com.frame.core.webapp.controller.account;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.core.components.AjaxResult;
import com.frame.core.components.UserAuthoritySubject;
import com.frame.core.entity.UserEntity;
import com.frame.core.query.xml.GeneralController;
import com.frame.core.query.xml.annoation.PageDefinition;
import com.frame.core.service.account.UserService;
import com.frame.core.utils.EncriptUtil;

@RequestMapping("/admin/users")
@Controller
@PageDefinition
public class UserController extends GeneralController<UserEntity> {
	private final static String DEFAULT_PASSWORD="233333";
	@Override
	public boolean beforeUpdate(UserEntity entity) {
		if (entity.getId()==null){
			entity.setUserPassword(EncriptUtil.encriptSHA1(entity.getUserLoginVerification()+DEFAULT_PASSWORD));
		}
		return true;
	}
	@Override
	public boolean beforeDelete(UserEntity entity) {
		if (entity.getId().equals(UserAuthoritySubject.<UserEntity>getAccountSubject().getId()))
			throw new RuntimeException("自己不能删除自己");
		return true;
	}
	@Autowired
	UserService service;

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
			service.registerUser(user);
			
		} catch (Exception e) {
			LOGGER.debug("注册失败");
			return new RuntimeException("用户名已存在！",e);
		}
		ajaxResult.setData(result.put("message", "2"));
		return ajaxResult;
	}
}
