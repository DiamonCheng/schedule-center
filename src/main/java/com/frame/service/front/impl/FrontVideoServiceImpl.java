package com.frame.service.front.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.core.dao.GeneralDao;
import com.frame.entity.media.VideoEntity;
import com.frame.entity.media.VideoUploadEntity;
import com.frame.model.dto.media.VideoDTO;
import com.frame.model.dto.media.VideoUploadDTO;
import com.frame.service.front.FrontVideoService;

/** 
* @author wowson
* @version create time：2017年3月24日 上午10:22:05 
* 类说明 ：
*/
@Service
public class FrontVideoServiceImpl implements FrontVideoService {
	@Autowired
	GeneralDao dao;
	@SuppressWarnings("unchecked")
	public void selectVideo(VideoDTO dto){
		String hql = "from VideoEntity order by createDateTime desc";
		List<VideoEntity> list = (List<VideoEntity>) dao.getHibernateTemplate().find(hql);
		dto.setList(list);
	}
	@Override
	public void selectVideoUpload(VideoUploadDTO dto) {
		String  hql ="from VideoUploadEntity order by createDateTime desc";
		@SuppressWarnings("unchecked")
		List<VideoUploadEntity> list = (List<VideoUploadEntity>) dao.getHibernateTemplate().find(hql);
		dto.setList(list);
		
	}
}
