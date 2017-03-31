package com.frame.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.core.dao.GeneralDao;
import com.frame.entity.media.ClassDynamicEntity;
import com.frame.service.admin.AdminClassDynamicService;

/** 
* @author wowson
* @version create time：2017年3月29日 下午9:14:19 
* 类说明 ：
*/
@Service
public class AdminClassDynamicServiceImpl implements AdminClassDynamicService{
	@Autowired
	GeneralDao dao;
	@Override
	public void save(ClassDynamicEntity entity) {
		dao.getHibernateTemplate().save(entity);
		
	}
	@Override
	public void update(ClassDynamicEntity entity) {
		String hql = "update ClassDynamicEntity set title = ? ,content = ? ,updateDateTime = ? where id= ?";
		int status=dao.getSessionFactory().getCurrentSession().createQuery(hql).setParameter(0, entity.getTitle())
			.setParameter(1, entity.getContent()).setTimestamp(2, entity.getUpdateDateTime())
			.setParameter(3, entity.getId()).executeUpdate();
		System.out.println(status);
	}

}
