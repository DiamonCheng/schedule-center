package com.frame.service.account;

import javax.transaction.Transactional;

/**
 * 
 * @author Defferson.Cheng
 *
 */
public interface UserService {
	/**
	 * 登陆
	 * @param userLoginVerification 用户登陆标志符
	 * @param userPassword 加密过的用户密码
	 * @return 0 登陆成功 1 登录失败
	 */
	int login(String userLoginVerification,String userPassword);
}
