package com.frame.core.service.account;

import java.util.List;

import com.frame.core.entity.MenuEntity;
import com.frame.core.entity.UserEntity;

public interface AuthorityService {
	public static final String NAVIGATION_OPTIONS_KEY="NAVIGATION_OPTIONS_KEY";
	public List<MenuEntity> getMenuListWithRole(UserEntity user);
	public List<MenuEntity> getMenuLocation(String requestURI);
	public boolean isAllowed(String[] parent,String child,UserEntity user);
}
