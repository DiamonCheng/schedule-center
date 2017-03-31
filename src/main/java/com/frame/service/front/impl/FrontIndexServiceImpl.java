package com.frame.service.front.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.core.dao.GeneralDao;
import com.frame.entity.media.IndexShowEntity;
import com.frame.service.front.FrontIndexService;

/** 
* @author wowson
* @version create time：2017年3月30日 下午6:42:05 
* 类说明 ：
*/
@Service
public class FrontIndexServiceImpl implements FrontIndexService {
	@Autowired
	GeneralDao dao;
	public IndexShowEntity get() {
		// TODO Auto-generated method stub
		List<IndexShowEntity> list = (List<IndexShowEntity>) dao.getHibernateTemplate().find("from IndexShowEntity where status = 1 ");
		return list.get(0);
	}

}
