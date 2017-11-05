package com.frame.core.service.account.impl;

import com.frame.core.dao.GeneralDao;
import com.frame.core.entity.MenuCheckable;
import com.frame.core.entity.MenuEntity;
import com.frame.core.entity.RoleEntity;
import com.frame.core.entity.UserEntity;
import com.frame.core.service.account.RoleService;
import com.frame.core.utils.TraverseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	GeneralDao dao;
	@SuppressWarnings("unchecked")
	@Override
	public void updateRoleMenus(List<MenuEntity> menuList, RoleEntity role) {
		role=dao.getHibernateTemplate().get(RoleEntity.class, role.getId());
		Object[] params=new Object[menuList.size()];
		StringBuilder sb=new StringBuilder("from MenuEntity where parent is null ");
		int i=0;
		for (MenuEntity m : menuList) {
			sb.append(" or id=? ");
			params[i++]=m.getId();
		}
		menuList=(List<MenuEntity>) dao.getHibernateTemplate().find(sb.toString(), params);
		if (role.getAlowMenus()==null)role.setAlowMenus(new HashSet<MenuEntity>());
		role.getAlowMenus().clear();
		for (MenuEntity menuEntity : menuList) {
			role.getAlowMenus().add(menuEntity);
		}
		dao.getHibernateTemplate().update(role);
	}
	@Override
	public List<MenuCheckable> getCheckableMenuList(RoleEntity role2) {
		if (role2.getId()!=null) role2 = dao.getHibernateTemplate().get(RoleEntity.class, role2.getId());
		else role2=new RoleEntity();
		final RoleEntity role=role2;
		if (role.getAlowMenus()==null) role.setAlowMenus(new HashSet<MenuEntity>());
		MenuEntity entity=dao.get("from MenuEntity where parent is null");
		MenuCheckable res=new MenuCheckable(); 
		TraverseUtil.traverseCopy(entity, res, "children", "children2", new TraverseUtil.TraverseCopyCallBack<MenuEntity, MenuCheckable>() {

			@Override
			public void afterCopy(MenuEntity currentSource, MenuCheckable currentTarget) {
				currentTarget.setParent(null);
				currentTarget.setChildren(null);
				boolean isContains=false;
				isContains=role.getAlowMenus().contains(currentSource);
				currentTarget.setChecked(isContains);
			}
		});
		return res.getChildren2();
	}
	@Override
	public List<RoleEntity> getUserRoles(UserEntity user){
		if (user.getId()==null) throw new NullPointerException("参数中的Id为空");
		dao.getHibernateTemplate().refresh(user);
		return new ArrayList<RoleEntity>(user.getRoles());
	}
}
