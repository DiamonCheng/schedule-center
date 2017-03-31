package com.frame.service.admin;

import com.frame.entity.media.IndexShowEntity;

/** 
* @author wowson
* @version create time：2017年3月30日 下午2:26:11 
* 类说明 ：
*/
public interface AdminIndexShowService {
	void save(IndexShowEntity entity);
	int update(IndexShowEntity entity);
	long countStautsIsTrue();
}
