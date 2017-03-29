package com.frame.service.front;

import com.frame.entity.media.MessageEntity;
import com.frame.entity.media.ReplyEntity;
import com.frame.model.dto.media.MessageDTO;

/** 
* @author wowson
* @version create time：2017年3月21日 下午7:26:19 
* 类说明 ：
*/

public interface MessageService {
	public void saveMsg(MessageEntity entity);
	public void selectMsg(MessageDTO dto);
	public Long countMsg(Long albumId);
	public void saveReply(ReplyEntity entity);
}
