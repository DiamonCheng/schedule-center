package com.frame.service.front;

import com.frame.core.entity.UserEntity;

/** 
* @author wowson
* @version create time：2017年3月21日 下午7:55:53 
* 类说明 ：
*/
public interface FrontUserService {
	UserEntity selectUser(Long Id);
	void update(UserEntity entity);
}
