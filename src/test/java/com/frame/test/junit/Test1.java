package com.frame.test.junit;

import com.frame.core.dao.GeneralDao;
import com.frame.core.entity.MenuEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class Test1 extends AbstractBaseTest {
	@Autowired
	GeneralDao dao;
	@Autowired 
	Gson gson;
	@Override
	public void dotest() throws Exception {
		String hql=
			"select m from UserEntity u join u.roles r join r.alowMenus m where u.id=2 and ( m.requestURI='/role/add')";
		
		
		System.out.println(dao.getHibernateTemplate().find(hql));
	}

}
