package com.frame.webapp.controller.admin.video;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frame.core.components.UserAuthoritySubject;
import com.frame.core.entity.UserEntity;
import com.frame.core.query.xml.GeneralController;
import com.frame.core.query.xml.annoation.PageDefinition;
import com.frame.entity.media.VideoEntity;

/** 
* @author wowson
* @version create time：2017年3月16日 下午3:44:35 
* 类说明 ： 后台视频外链 控制器
*/
@Controller
@RequestMapping("/admin/video/foreign")
@PageDefinition("adminVideoForeignDefinetion.xml")
public class AdminVideoForeignController extends GeneralController<VideoEntity>{
	@Override
	public boolean beforeUpdate(VideoEntity entity) {
		if (entity.getId()==null){
			entity.setCreateUserKey(UserAuthoritySubject.<UserEntity>getAccountSubject().getId());
		}
		return true;
	}
	public boolean beforeDelete(VideoEntity entity) {
		System.out.println(UserAuthoritySubject.<UserEntity>getAccountSubject().getId());
		return true;
	}
}
