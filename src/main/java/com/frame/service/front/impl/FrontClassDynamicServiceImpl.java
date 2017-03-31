package com.frame.service.front.impl;

import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.core.dao.GeneralDao;
import com.frame.entity.media.ClassDynamicEntity;
import com.frame.model.dto.media.ClassDynamicDTO;
import com.frame.model.vo.media.ClassDynamicVO;
import com.frame.service.front.FrontClassDynamicService;

/** 
* @author wowson
* @version create time：2017年3月30日 下午7:39:09 
* 类说明 ：
*/
@Service
public class FrontClassDynamicServiceImpl implements FrontClassDynamicService {
	@Autowired
	GeneralDao dao;
	@Override
	public void select(ClassDynamicDTO dto) {
		int firstResult = (int) ((dto.getPage()-1)*dto.getPageSize());
		int maxResult = (int) (dto.getPageSize()*1);
		String hql ="select c.title as title,c.id as classDynamicId ,c.content as content ,c.updateDateTime as updateDateTime from ClassDynamicEntity c  where 1 =1 ";
		String hsqcount = "select count(*) from ClassDynamicEntity where 1=1";
		@SuppressWarnings("unchecked")
		List<ClassDynamicVO> list = dao.getSessionFactory().getCurrentSession().createQuery(hql)
				.setResultTransformer(Transformers.aliasToBean(ClassDynamicVO.class)).setFirstResult(firstResult)
				.setMaxResults(maxResult).list();
		dto.setList(list);
		Long count = dao.getUnique(hsqcount);
		dto.setTotalCount(count);
		dto.setTotalPage((count + dto.getPageSize()-1)/dto.getPageSize());
	}
	@Override
	public ClassDynamicEntity get(Long id) {
		return dao.getHibernateTemplate().get(ClassDynamicEntity.class, id);
	}

}
