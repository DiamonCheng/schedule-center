package com.frame.core.components;


import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;


public abstract class DaoSupport extends HibernateDaoSupport {
	@Autowired
	public void setSessionFactory2(SessionFactory sessionFactory){setSessionFactory(sessionFactory);}
	@SuppressWarnings("unchecked")
	public <T> T getUnique(String hql,Object... params){
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		for (int i=0;i<params.length;i++) {
			query.setParameter(i, params[i]);
		}
		return (T) query.uniqueResult();
	}
}
