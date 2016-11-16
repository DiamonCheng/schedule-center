package com.frame.service.account.impl;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.util.StringUtils;

import com.frame.core.components.UserAuthoritySubject;
import com.frame.core.utils.AesEncryptUtils;
import com.frame.core.utils.EncriptUtil;
import com.frame.dao.GeneralDao;
import com.frame.service.account.UserService;
@Service
@Transactional(value=TxType.REQUIRED)
@org.springframework.transaction.annotation.Transactional(propagation=Propagation.REQUIRED)
public class UserServiceImpl implements UserService{
	private final static Logger LOGGER=LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	GeneralDao dao;
	@Override
	public int login(String userLoginVerification, String userPassword) {
		if (StringUtils.isEmpty(userLoginVerification)||StringUtils.isEmpty(userPassword)) return 1;
		try {
			userPassword=AesEncryptUtils.aesDecrypt(userPassword);
		} catch (Exception e) {
			LOGGER.error("解密密码出现异常",e);
			return 1;
		}
		userPassword=EncriptUtil.encriptSHA1(userPassword);
		List<?> list=dao.getHibernateTemplate().find("from UserEntity where userLoginVerification=? and userPassword=?", userLoginVerification,userPassword);
		if (list.size()>0){
			UserAuthoritySubject.setAccountSubject(list.get(0));
			//TODO 加载角色，加载权限
			return 0;
		}
		return 1;
	}

}
