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
import com.frame.entity.media.IndexShowEntity;
import com.frame.service.admin.AdminIndexShowService;

/**
 * @author wowson
 * @version create time：2017年3月30日 上午10:10:56 类说明 ：
 */
@Controller
@RequestMapping("/admin/index/mana")
@PageDefinition("indexManageDefinetion.xml")
public class AdminIndexmanController extends GeneralController<IndexShowEntity> {
	@Autowired
	AdminIndexShowService service;

	@RequestMapping("/custsaveOrUpdate")
	@ResponseBody
	public Object customizeSave(String title, String content, Long edid, Long edstatus) {
		AjaxResult result = new AjaxResult();
		if (StringUtils.isEmpty(title)) {
			result.setCode("-1");
			result.setMessage("标题没有填");
			return result;
		}
		if (StringUtils.isEmpty(content)) {
			result.setCode("-1");
			result.setMessage("麻烦你写点内容好吗！");
			return result;
		}
		/*if (StringUtils.isEmpty(edstatus)) {
			result.setCode("-1");
			result.setMessage("选择是否禁用！");
			return result;
		}*/
		if (edstatus == 1)
			if (service.countStautsIsTrue() > 0) {
				result.setCode("-1");
				result.setMessage("只能有一条描述在启用状态！这条就禁用吧");
				return result;
			}
		IndexShowEntity entity = new IndexShowEntity();
		UserEntity user = UserAuthoritySubject.getAccountSubject();
		entity.setStatus(edstatus);
		entity.setUserId(user.getId());
		entity.setContent(content);
		entity.setTitle(title);
		entity.setUpdateDateTime(new Date());
		if (StringUtils.isEmpty(edid)) {
			service.save(entity);
		} else {
			entity.setId(edid);
			service.update(entity);
		}
		result.setMessage("保存成功！");
		return result;
	}
}
