package com.frame.core.webapp.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frame.core.components.UserAuthoritySubject;
import com.frame.core.entity.RoleEntity;
import com.frame.core.query.xml.GeneralController;
import com.frame.core.query.xml.annoation.PageDefinition;
@Controller
@RequestMapping("/role")
@PageDefinition("rolePageDefinition.xml")
public class RoleController extends GeneralController<RoleEntity> {
	@Override
	public boolean beforeDelete(RoleEntity entity) {
		throw new RuntimeException("未知原因。");
	}
	@Override
	public boolean beforeUpdate(RoleEntity entity) {
//		UserAuthoritySubject.getSession().setAttribute("error", "未知原因2");
		return true;
	}
}
