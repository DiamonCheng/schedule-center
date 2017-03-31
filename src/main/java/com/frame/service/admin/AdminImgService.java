package com.frame.service.admin;

import java.util.List;

import com.frame.entity.media.ImgEntity;
import com.frame.entity.media.ImgGroupEntity;

/** 
* @author wowson
* @version create time：2017年3月14日 下午10:13:36 
* 类说明 ： 后台相册、图片处理 增删改查
*/
public interface AdminImgService {
	/**
	 *  新增
	 * @param entity  
	 *        实体 ImgEntity
	 */
	void addImgInfo(ImgEntity entity);
	/**
	 * 
	 * @param entity 
	 *       实体 ImgEntity
	 * @return true 删除成功  false 删除 失败
	 */
	boolean deleteImgInfo(ImgEntity entity);
	public ImgEntity selectImgEntityByID(Long key);
	public List<ImgEntity> selectImgEntityByIGKey(Long key);
	/**
	 * 
	 * @param entity 
	 *     实体 ImgGroupEntity
	 * @return true 删除成功  false 删除 失败
	 */
	boolean deleteImgGroupInfo(ImgGroupEntity entity);
}
