package com.frame.model.vo.media;

import java.util.Date;
import java.util.List;

import com.frame.entity.media.MessageEntity;

/** 
* @author wowson
* @version create time：2017年3月21日 下午11:00:34 
* 类说明 ：
*/
public class MessageVO extends MessageEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private Long userId;
	private Date createTime;
	private Long messageId;
	private List<ReplyVO> replyVO;

	public List<ReplyVO> getReplyVO() {
		return replyVO;
	}

	public void setReplyVO(List<ReplyVO> replyVO) {
		this.replyVO = replyVO;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	
}
