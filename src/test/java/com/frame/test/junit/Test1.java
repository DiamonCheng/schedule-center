package com.frame.test.junit;

import com.frame.core.dao.GeneralDao;
import com.frame.core.entity.MenuEntity;
import org.springframework.beans.factory.annotation.Autowired;

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
