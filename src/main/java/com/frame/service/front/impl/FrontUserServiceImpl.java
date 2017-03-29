package com.frame.service.front.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.core.dao.GeneralDao;
import com.frame.core.entity.UserEntity;
import com.frame.service.front.FrontUserService;

/** 
* @author wowson
* @version create time：2017年3月21日 下午7:56:31 
* 类说明 ：
*/
@Service
public class FrontUserServiceImpl implements FrontUserService {
	@Autowired
	GeneralDao dao;
	@Override
	public UserEntity selectUser(Long Id) {
		// TODO Auto-generated method stub
		return dao.getHibernateTemplate().get(UserEntity.class, Id);
	}
		
}
