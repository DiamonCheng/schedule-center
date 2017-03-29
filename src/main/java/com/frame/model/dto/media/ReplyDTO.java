package com.frame.model.dto.media;

import java.util.List;

import com.frame.model.vo.media.ReplyVO;

/** 
* @author wowson
* @version create time：2017年3月22日 下午10:01:09 
* 类说明 ：
*/
public class ReplyDTO {
	private List<ReplyVO> reply;
	private Long albumId;
	private Long userId;
	private Long messageId;
	
	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<ReplyVO> getReply() {
		return reply;
	}

	public void setReply(List<ReplyVO> reply) {
		this.reply = reply;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	
}
