package com.frame.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.core.dao.GeneralDao;
import com.frame.entity.media.IndexShowEntity;


/** 
* @author wowson
* @version create time：2017年3月30日 下午2:26:51 
* 类说明 ：
*/
@Service
public class AdminIndexShowServiceImpl implements AdminIndexShowService {
	@Autowired
	GeneralDao dao;

	@Override
	public void save(IndexShowEntity entity) {
		dao.getHibernateTemplate().save(entity);
		
	}

	@Override
	public int update(IndexShowEntity entity) {
		String hql = "update IndexShowEntity set title = ? , content = ? , status = ?,updateDateTime = ? where id = ? ";
		int num = dao.getSessionFactory().getCurrentSession().createQuery(hql).setParameter(0, entity.getTitle())
				.setParameter(1, entity.getContent()).setLong(2, entity.getStatus())
				.setTimestamp(3, entity.getUpdateDateTime()).setLong(4, entity.getId()).executeUpdate();
		return num;
	}

	@Override
	public long countStautsIsTrue() {
		String hql ="select count(*) from IndexShowEntity where status = 1";
		return dao.getUnique(hql);
	}

	@Override
	public IndexShowEntity get(long id) {
		return dao.getHibernateTemplate().get(IndexShowEntity.class, id);
	}
}
