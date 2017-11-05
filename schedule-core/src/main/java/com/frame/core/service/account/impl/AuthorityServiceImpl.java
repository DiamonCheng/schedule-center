package com.frame.core.service.account.impl;

import com.frame.core.dao.GeneralDao;
import com.frame.core.entity.MenuEntity;
import com.frame.core.entity.RoleEntity;
import com.frame.core.entity.UserEntity;
import com.frame.core.service.account.AuthorityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	GeneralDao dao;
	@SuppressWarnings({ "unchecked", "rawtypes" }) 
	public List<MenuEntity> getMenuListWithRole(UserEntity user){
		//List.size()=0 list.get0 =mainpage
		Set<RoleEntity> roleSet=user.getRoles();
		Set<MenuEntity> menuSet=new HashSet<MenuEntity>();
		for (RoleEntity roleEntity : roleSet) {
			menuSet.addAll(roleEntity.getAlowMenus());
		}
		List list=dao.getHibernateTemplate().find("select child from MenuEntity p join p.children as child where p.parent is null");
		return filtMenu(list,menuSet);
	}
	private List<MenuEntity> filtMenu(List<MenuEntity> list,Set<MenuEntity> menuSet){
		if (list==null||list.size()==0) return null;
		List<MenuEntity> resList=new ArrayList<MenuEntity>();
		for (MenuEntity menuEntity : list) {
			if (menuSet.contains(menuEntity)){
				MenuEntity menuCopy=new MenuEntity();
				BeanUtils.copyProperties(menuEntity,menuCopy,"children","parent");
				menuCopy.setChildren(filtMenu(menuEntity.getChildren(),menuSet));
				resList.add(menuCopy);
			}
		}
		return resList;
	}
	public List<MenuEntity> getMenuLocation(String requestURI){
		if (requestURI.charAt(requestURI.length()-1)=='/') requestURI=requestURI.substring(0, requestURI.length()-1);
		MenuEntity target=dao.get("from MenuEntity where requestURI like ? and LENGTH(requestURI)<=? order by id desc", requestURI+"%",requestURI.length()+1);
		List<MenuEntity> menuList=new ArrayList<MenuEntity>();
		if (target!=null){
			menuList.add(target);
			while(target.getParent()!=null){
				target=target.getParent();
				MenuEntity targetCopy=new MenuEntity();
				targetCopy.setDisplayName(target.getDisplayName());
				targetCopy.setRequestURI(target.getRequestURI());
				targetCopy.setId(target.getId());
				menuList.add(targetCopy);
			}
			Collections.reverse(menuList);
		}
		return menuList;
	}
	public boolean isAllowed(String[] parent,String child,UserEntity user){
		Object[] params=new Object[parent.length+1];
		params[0]=user.getId();
		int index=0;
		StringBuilder hql=new StringBuilder("select count(m) from UserEntity u join u.roles r join r.alowMenus m where u.id=? and ( ");
		for (String string : parent) {
			params[index+1]=string+child; 
			if (index!=0) hql.append("or ");
			hql.append("m.requestURI=? ");
		}
		hql.append(')');
		long count=dao.getUnique(hql.toString(), params);
		return count>0;
	}
}
