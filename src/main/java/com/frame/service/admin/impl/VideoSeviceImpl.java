package com.frame.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.core.dao.GeneralDao;
import com.frame.entity.media.VideoUploadEntity;
import com.frame.service.admin.VideoService;

/** 
* @author wowson
* @version create time：2017年3月27日 下午2:01:36 
* 类说明 ：
*/
@Service
public class VideoSeviceImpl implements VideoService{
	@Autowired
	GeneralDao dao;
	public void save(VideoUploadEntity entity){
		dao.getHibernateTemplate().save(entity);
	}
}
