package com.frame.core.components;


import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T get(String hql,Object... params){
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		for (int i=0;i<params.length;i++) {
			query.setParameter(i, params[i]);
		}
		List list=query.setMaxResults(1).list();
		if (list.size()>0) return (T) list.get(0);
		return null;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map<String,Object>> findMap(String hql, Object... params){
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		for (int i=0;i<params.length;i++) {
			query.setParameter(i, params[i]);
		}
		List<Map<String,Object>> list =query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}
	public int executeHql(String hql,Object... params){
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		for (int i=0;i<params.length;i++) {
			query.setParameter(i, params[i]);
		}
		return query.executeUpdate();
	}
}
