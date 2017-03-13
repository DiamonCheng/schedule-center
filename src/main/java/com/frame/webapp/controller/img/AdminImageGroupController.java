package com.frame.webapp.controller.img;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frame.core.components.UserAuthoritySubject;
import com.frame.core.entity.UserEntity;
import com.frame.core.query.xml.GeneralController;
import com.frame.core.query.xml.annoation.PageDefinition;
import com.frame.entity.media.ImgGroupEntity;

/** 
* @author wowson
* @version create time：2017年3月10日 下午4:09:08 
* 类说明 ：
*/
@Controller
@RequestMapping("/admin/menu/img/group")
@PageDefinition("imgGroupDefinetion.xml")
public class AdminImageGroupController extends GeneralController <ImgGroupEntity> {
	@Override
	public boolean beforeUpdate(ImgGroupEntity entity) {
		if (entity.getId()==null){
			entity.setCreateUserKey(UserAuthoritySubject.<UserEntity>getAccountSubject().getId());
		}
		return true;
	}
	public boolean beforeDelete(ImgGroupEntity entity){
		System.out.println("beforeDelete ImgGroupEntity id："+entity.getId());
		return true;
	}
}