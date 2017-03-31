package com.frame.webapp.controller.admin.editor;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.core.components.AjaxResult;
import com.frame.core.components.UserAuthoritySubject;
import com.frame.core.entity.UserEntity;
import com.frame.core.query.xml.GeneralController;
import com.frame.core.query.xml.annoation.PageDefinition;
import com.frame.entity.media.ClassDynamicEntity;
import com.frame.service.admin.AdminClassDynamicService;

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
	@Autowired
	AdminClassDynamicService service;
	@RequestMapping("/custsaveOrUpdate")
	@ResponseBody
	public Object customizeSave(String title,String content,String edid){
		AjaxResult result = new AjaxResult();
		if(StringUtils.isEmpty(title)){
			result.setCode("-1");
			result.setMessage("标题没有填");
			return result;
		}
		if(StringUtils.isEmpty(content)){
			result.setCode("-1");
			result.setMessage("麻烦你写点内容好吗！");
			return result;
		}
		ClassDynamicEntity entity = new ClassDynamicEntity();
		UserEntity user =UserAuthoritySubject.getAccountSubject();
		entity.setUserId(user.getId());;
		entity.setContent(content);
		entity.setTitle(title);
		entity.setUpdateDateTime(new Date());
		if(StringUtils.isEmpty(edid)){
			service.save(entity);
		}else{
			entity.setId(Long.parseLong(edid));
			service.update(entity);
		}
		result.setMessage("保存成功！");
		return result;
	}
}
