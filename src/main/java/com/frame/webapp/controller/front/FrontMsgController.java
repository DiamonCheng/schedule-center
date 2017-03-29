package com.frame.webapp.controller.front;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.core.components.AjaxResult;
import com.frame.core.components.UserAuthoritySubject;
import com.frame.core.entity.UserEntity;
import com.frame.entity.media.MessageEntity;
import com.frame.entity.media.ReplyEntity;
import com.frame.model.dto.media.MessageDTO;
import com.frame.service.front.MessageService;

/** 
* @author wowson
* @version create time：2017年3月21日 下午7:40:30 
* 类说明 ：
*/
@Controller
@RequestMapping("/front/msg")
public class FrontMsgController {
	@Autowired
	MessageService service;
	@RequestMapping("/ansc")
	@ResponseBody
	public Object msgShow(MessageDTO dto){
		service.selectMsg(dto);
		return dto;
	}
	@RequestMapping("/save")
	@ResponseBody
	public Object save(HttpServletRequest request){
		AjaxResult result = new AjaxResult();
		String albumId = request.getParameter("albumId");
		String content = request.getParameter("content");
		MessageEntity entity = new MessageEntity();
		UserEntity user  = (UserEntity) UserAuthoritySubject.getAccountSubject();
		if(!StringUtils.isEmpty(content) && !StringUtils.isEmpty(albumId)){
			entity.setAlbumId(Long.parseLong(albumId));
			entity.setContent(content);
			entity.setCreateDateTime(new Date());
			entity.setUser(user);
			service.saveMsg(entity);
		}
		return result;
	}
	
	@RequestMapping("/saveReply")
	@ResponseBody
	public Object saveReply(ReplyEntity entity){
		AjaxResult result = new AjaxResult();
		entity.setCreateDateTime(new Date());
		entity.setUser((UserEntity) UserAuthoritySubject.getAccountSubject());
		service.saveReply(entity);
		return result;
	}
}
