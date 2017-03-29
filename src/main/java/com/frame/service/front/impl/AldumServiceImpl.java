package com.frame.service.front.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.frame.core.dao.GeneralDao;
import com.frame.entity.media.ImgEntity;
import com.frame.entity.media.ImgGroupEntity;
import com.frame.service.front.AldumService;
import com.frame.service.front.MessageService;

/**
 * @author wowson
 * @version create time：2017年3月20日 下午3:25:32 类说明 ：
 */
@Service
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED)
public class AldumServiceImpl implements AldumService {
	@Autowired
	GeneralDao dao;
	@Autowired
	MessageService msgService;

	@SuppressWarnings("unchecked")
	@Override
	public List<ImgGroupEntity> selectAldum(Map<String, Object> data) {
		List<ImgGroupEntity> list = (List<ImgGroupEntity>) dao.getHibernateTemplate().find("from ImgGroupEntity");
		String basePath = data.get("basePath") + ""; // 准确来说是baseUrl
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String hql = "from ImgEntity where imgGroupKey = " + list.get(i).getId() + " ";
				// 查相册第一张图片作预览图
				List<ImgEntity> listImg = dao.getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult(0).setMaxResults(1).list();
				if (listImg.size() > 0) {
					list.get(i).setPreviewPic(basePath +"/"+ listImg.get(0).getSavePath()+"/"+listImg.get(0).getImgName());
					// 统计相片
					list.get(i).setCountImg(countImg(list.get(i).getId()));
					list.get(i).setCountMsg(msgService.countMsg(list.get(i).getId()));
				}
			}
		}
		return (List<ImgGroupEntity>) list;
	}

	@Override
	public List<ImgEntity> selectAldumPic(Map<String, Object> data) {
		Long imgGroupKey = Long.parseLong(data.get("imgGroupKey") + "");

		String hql = "from ImgEntity where imgGroupKey = :imgGroupKey ";
		int page = Integer.parseInt(data.get("page") + "");
		int pageSize = Integer.parseInt(data.get("pageSize") + "");

		@SuppressWarnings("unchecked")
		List<ImgEntity> list = (List<ImgEntity>) dao.getSessionFactory().getCurrentSession().createQuery(hql).setParameter("imgGroupKey", imgGroupKey).setFirstResult((page - 1) * pageSize)
				.setMaxResults(pageSize).list();
		return list;
	}

	@Override
	public ImgGroupEntity getAldum(String id) {
		return (ImgGroupEntity) dao.getHibernateTemplate().get(ImgGroupEntity.class, Long.parseLong(id));
	}

	@Override
	public Long countImg(Long imgGroupKey) {
		String hql = "select count(*) from ImgEntity where 1=1 and imgGroupKey = ? ";
		return dao.getUnique(hql, imgGroupKey);
		// return (int)
		// dao.getSessionFactory().getCurrentSession().createQuery(hql).uniqueResult();
	}

}
