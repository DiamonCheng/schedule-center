package com.frame.core.webapp.controller.account;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.core.components.AjaxResult;
import com.frame.core.entity.MenuCheckable;
import com.frame.core.entity.MenuEntity;
import com.frame.core.entity.RoleEntity;
import com.frame.core.query.xml.GeneralController;
import com.frame.core.query.xml.annoation.PageDefinition;
import com.frame.core.service.account.RoleService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
@Controller
@RequestMapping("/role")
@PageDefinition("rolePageDefinition.xml")
public class RoleController extends GeneralController<RoleEntity> {
	@Override
	public boolean beforeDelete(RoleEntity entity) {
		return true;
	}
	@Override
	public boolean beforeUpdate(RoleEntity entity) {
//		UserAuthoritySubject.getSession().setAttribute("error", "未知原因2");
		return true;
	}
	@Autowired
	RoleService roleService;
	@ResponseBody
	@RequestMapping("/loadMenuTree")
	public List<MenuCheckable> loadMenuTree(RoleEntity role){
		List<MenuCheckable> list=roleService.getCheckableMenuList(role);
		gson.toJson(list);
		return list;
	}
	@Autowired
	Gson gson;
	
	@ResponseBody
	@RequestMapping("/saveMenuTree")
	public Object saveMenuTree(String menuList,RoleEntity role){
		List<MenuEntity> menuList2=gson.fromJson(menuList, new TypeToken<List<MenuEntity>>(){}.getType());
		roleService.updateRoleMenus(menuList2, role);
		return new AjaxResult();
	}
}
