package com.frame.core.webapp.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frame.core.entity.UserEntity;
import com.frame.core.query.xml.GeneralController;
import com.frame.core.query.xml.annoation.PageDefinition;
import com.frame.core.utils.EncriptUtil;

@RequestMapping("/users")
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
//		if (entity.getId().equals(UserAuthoritySubject.<UserEntity>getAccountSubject().getId()))
//			throw new RuntimeException("自己不能删除自己");
		return true;
	}
}
