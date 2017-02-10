package com.frame.test.junit;

import com.frame.dao.GeneralDao;
import com.frame.entity.MenuEntity;
import org.springframework.beans.factory.annotation.Autowired;

import com.frame.core.query.xml.definition.QueryConditions;
import com.frame.webapp.controller.menu.MenuController;

public class Test1 extends AbstractBaseTest {
	@Autowired
	GeneralDao dao;
	@Override
	public void dotest() throws Exception {
		MenuEntity entity=new MenuEntity();
		entity.setRequestURI("23333");
		entity.setDisplayName("displayName2");
		MenuEntity parent=new MenuEntity();
		parent.setId(1L);
		entity.setParent(parent);
		dao.getHibernateTemplate().save(entity);
		System.out.println(entity.getId());
	}

}
