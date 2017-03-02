package com.frame.core.service.account;

import java.util.List;

import com.frame.core.entity.MenuCheckable;
import com.frame.core.entity.MenuEntity;
import com.frame.core.entity.RoleEntity;
import com.frame.core.entity.UserEntity;

public interface RoleService {
	void updateRoleMenus(List<MenuEntity> menuList,RoleEntity role);
	List<MenuCheckable> getCheckableMenuList(RoleEntity role);
	List<RoleEntity> getUserRoles(UserEntity user);
}
