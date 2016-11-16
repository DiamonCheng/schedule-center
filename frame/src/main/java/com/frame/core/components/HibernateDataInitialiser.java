package com.frame.core.components;


import java.lang.reflect.Field;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class HibernateDataInitialiser extends HibernateDaoSupport{
	private int countToflush=50;
	public static class HibernateDataInitException extends RuntimeException{
		private static final long serialVersionUID = 6368101378580489242L;
		public HibernateDataInitException(String message,Throwable t) {
			super(message, t);
		}
	}
	public interface InitDataProvider{
		Object[][] getObjectList();
	}
	public HibernateDataInitialiser(SessionFactory sessionFactory,InitDataProvider provider) {
		this.setSessionFactory(sessionFactory);
		Session session=sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Object[][] objss=provider.getObjectList();
			if (objss==null) return;
			for (Object[] objects : objss) {
				int count=0;
				for (Object object:objects) {
					List<?> list=this.getHibernateTemplate().find("from "+object.getClass().getName()+" where initId=?",getInitId(object) );
					if (list.size()==0){
						session.save(object);
						if ((count++)%countToflush==0) session.flush(); 
					}
				}
				
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new HibernateDataInitException("初始化系统数据时发生异常，系统数据将会回滚到未启动状态。", e);
		}finally {
			session.close();
		}
	}
	public void setCountToflush(int countToflush) {
		this.countToflush = countToflush;
	}
	private static Object getInitId(Object o) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		Class<?> cls=o.getClass();
		while(!(cls=cls.getSuperclass()).isAssignableFrom(BaseEntity.class));
		Field f=cls.getDeclaredField("initId");
		f.setAccessible(true);
		return f.get(o);
	}
	
}
