package com.frame.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.frame.core.dao.GeneralDao;
import com.frame.entity.media.ImgEntity;
import com.frame.entity.media.ImgGroupEntity;
import com.frame.service.admin.AdminImgService;
import com.frame.utils.ImgDirConfig;
import com.frame.utils.FileUtils;

/**
 * @author wowson
 * @version create time：2017年3月14日 下午10:14:24 类说明 ：
 */
@Service
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED)
public class AdminImgServiceImpl implements AdminImgService {
	@Autowired
	GeneralDao dao;

	@Override
	public void addImgInfo(ImgEntity entity) {
		dao.getHibernateTemplate().save(entity);
	}

	public ImgEntity selectImgEntityByID(Long key) {
		ImgEntity entity = dao.getHibernateTemplate().get(ImgEntity.class, key);
		return entity;
	}

	public boolean deleteImgInfo(ImgEntity entity) {
		boolean flag = true;
		ImgEntity imgEntity = selectImgEntityByID(entity.getId());
		String diskPath = ImgDirConfig.SAVE_PIC_DIR + "/" + imgEntity.getImgName();
		// 本地图片删除成功后 删除数据库记录
		if (FileUtils.deleteLocalFile(diskPath))
			dao.getHibernateTemplate().delete(imgEntity);
		else {
			System.out.println("删除失败！");
			flag = false;
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImgEntity> selectImgEntityByIGKey(Long key) {
		List<ImgEntity> list = (List<ImgEntity>) dao.getHibernateTemplate().find("from ImgEntity where imgGroupKey = ?", key);
		return list;
	}

	@Override
	public boolean deleteImgGroupInfo(ImgGroupEntity entity) {
		boolean flag = true;
		Long imgGroupKey = entity.getId();
		List<ImgEntity> listImg = selectImgEntityByIGKey(imgGroupKey);
		String diskPath = ImgDirConfig.SAVE_PIC_DIR;
		if (!FileUtils.isEmpty(diskPath)) // 判断文件夹是否为空
			for (int i = 0; i < listImg.size(); i++) {
				diskPath = ImgDirConfig.SAVE_PIC_DIR +"/"+ listImg.get(i).getImgName();
				if (FileUtils.deleteLocalFile(diskPath))
					dao.getHibernateTemplate().delete(listImg.get(i));
				else {
					System.out.println("查无此图片！");
					flag = false;
				}
			}
		// 无论文件夹是否为空都删除相册
		// dao.getHibernateTemplate().delete(entity);
		/*
		 * String hql = " delete from ImgGroupEntity where id = :key "; int x=
		 * dao.getSessionFactory().getCurrentSession().createQuery(hql).
		 * setParameter("key", entity.getId()).executeUpdate();
		 * System.out.println(x);
		 */
		return flag;
	}
}
