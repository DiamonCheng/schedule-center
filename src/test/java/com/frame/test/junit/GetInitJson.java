package com.frame.test.junit;

import com.frame.core.dao.GeneralDao;
import com.frame.core.entity.MenuEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class GetInitJson extends AbstractBaseTest {
	@Autowired
	GeneralDao dao;
	@Autowired
	Gson gson;
	@Override
	public void dotest() throws Exception {
		System.out.println(gson.toJson(dao.getHibernateTemplate().find("from MenuEntity where parent=null"), new TypeToken<List<MenuEntity>>(){}.getType()));
		System.out.println(gson.toJson(dao.getHibernateTemplate().find("from UserEntity"), new TypeToken<List<MenuEntity>>(){}.getType()));
//		System.out.println(gson.toJson(dao.getHibernateTemplate().find("from MenuEntity"), new TypeToken<List<MenuEntity>>(){}.getType()));
	}

}
