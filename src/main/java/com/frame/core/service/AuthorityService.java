package com.frame.core.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.core.dao.GeneralDao;
import com.frame.core.entity.MenuEntity;

@Service
@Transactional
public class AuthorityService {
	public static final String NAVIGATION_OPTIONS_KEY="NAVIGATION_OPTIONS_KEY";
	@Autowired
	GeneralDao dao;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<MenuEntity> getMenuList(){
		//List.size()=0 list.get0 =mainpage
		List list=dao.getHibernateTemplate().find("select child from MenuEntity p join p.children as child where p.parent is null");
		return filtMenu(list);
	}
	private List<MenuEntity> filtMenu(List<MenuEntity> list){
		if (list==null||list.size()==0) return null;
		List<MenuEntity> resList=new ArrayList<MenuEntity>();
		for (MenuEntity menuEntity : list) {
			//TODO 如果含有权限
			MenuEntity menuCopy=new MenuEntity();
			BeanUtils.copyProperties(menuEntity,menuCopy,"children","parent");
			menuCopy.setChildren(filtMenu(menuEntity.getChildren()));
			resList.add(menuCopy);
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
	public boolean isAllowed(String[] parent,String child){
		Object[] urls=new String[parent.length];
		int index=0;
		StringBuilder hql=new StringBuilder("select count(0) from MenuEntity where  ");
		for (String string : parent) {
			urls[index]=string+child;
			if (index!=0) hql.append("or ");
			hql.append("requestURI=? ");
		}
		long count=dao.getUnique(hql.toString(), urls);
		return count>0;
	}
}
