package com.frame.service.front;

import java.util.List;
import java.util.Map;

import com.frame.entity.media.ImgEntity;
import com.frame.entity.media.ImgGroupEntity;

/** 
* @author wowson
* @version create time：2017年3月20日 下午3:17:59 
* 类说明 ：
*/
public interface AldumService {
	List<ImgGroupEntity> selectAldum(Map<String,Object> data);
	List<ImgEntity> selectAldumPic(Map<String,Object> data);
	ImgGroupEntity getAldum(String id);
	Long countImg(Long imgGroupKey);
}
