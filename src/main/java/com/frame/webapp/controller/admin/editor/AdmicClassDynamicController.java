package com.frame.webapp.controller.admin.editor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frame.core.components.UserAuthoritySubject;
import com.frame.core.entity.UserEntity;
import com.frame.core.query.xml.GeneralController;
import com.frame.core.query.xml.annoation.PageDefinition;
import com.frame.entity.media.ClassDynamicEntity;
import com.frame.entity.media.ImgGroupEntity;

/** 
* @author wowson
* @version create time：2017年3月29日 上午10:05:33 
* 类说明 ：    
*/
@Controller
@RequestMapping("/admin/editor/class")
@PageDefinition("classDynamicDefinetion.xml")
public class AdmicClassDynamicController extends GeneralController<ClassDynamicEntity>{
	public boolean beforeUpdate(ClassDynamicEntity entity) {
		if (entity.getId() == null) {
			entity.setUserId(UserAuthoritySubject.<UserEntity>getAccountSubject().getId());
		}
		return true;
	}
	public boolean beforeDelete(){
		return true;
	}
}
