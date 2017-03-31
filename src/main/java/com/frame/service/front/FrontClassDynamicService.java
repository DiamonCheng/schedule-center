package com.frame.service.front;

import com.frame.entity.media.ClassDynamicEntity;
import com.frame.model.dto.media.ClassDynamicDTO;

/** 
* @author wowson
* @version create time：2017年3月30日 下午7:38:19 
* 类说明 ：
*/
public interface FrontClassDynamicService {
 public void select(ClassDynamicDTO dto);
 public ClassDynamicEntity get(Long id);
}
