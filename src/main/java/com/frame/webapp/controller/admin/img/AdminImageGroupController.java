package com.frame.webapp.controller.admin.img;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.frame.entity.media.ImgEntity;
import com.frame.entity.media.ImgGroupEntity;
import com.frame.service.admin.AdminImgService;
import com.frame.utils.ImgDirConfig;
import com.frame.utils.FileUtils;

/**
 * @author wowson
 * @version create time：2017年3月10日 下午4:09:08 类说明 ：
 */
@Controller
@RequestMapping("/admin/menu/img/group")
@PageDefinition("adminImgGroupDefinetion.xml")
public class AdminImageGroupController extends GeneralController<ImgGroupEntity> {
	@Override
	public boolean beforeUpdate(ImgGroupEntity entity) {
		if (entity.getId() == null) {
			entity.setCreateUserKey(UserAuthoritySubject.<UserEntity>getAccountSubject().getId());
		}
		return true;
	}

	@Autowired
	AdminImgService service;

	public boolean beforeDelete(ImgGroupEntity entity) {
		boolean flag = true;
		if (entity.getId() != null) {
			service.deleteImgGroupInfo(entity);
		}
		return flag;
	}

	@Autowired
	AdminImgService imgService;

	@RequestMapping("/xhr/delete")
	@ResponseBody
	public Object deleteImgGroupInfo(HttpServletRequest request) {
		AjaxResult result = new AjaxResult();
		ImgGroupEntity entity = new ImgGroupEntity();
		String id = request.getParameter("id");
		if (StringUtils.isEmpty(id))
			result.setMessage("id is Null!");
		else {
			entity.setId(Long.parseLong(id));
			if (imgService.deleteImgGroupInfo(entity)) {
				result.setMessage("删除成功！");
			}
		}
		return result;
	}
}