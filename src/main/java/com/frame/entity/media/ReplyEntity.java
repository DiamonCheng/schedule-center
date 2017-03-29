package com.frame.entity.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.frame.core.components.BaseEntity;
import com.frame.core.entity.UserEntity;

/** 
* @author wowson
* @version create time：2017年3月22日 下午9:17:16 
* 类说明 ：
*/
@Entity
@Table(name="sys_reply")
public class ReplyEntity extends BaseEntity{
	
	private String content;
	@Column(name="message_id")
	private Long messageId;
	@Column(name="reply_user_id")
	private Long replyUserId;  //回复的用户id
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="UserId",foreignKey = @ForeignKey(name="null"))
	private UserEntity user;  //回复用户
	/*public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {														
		this.userId = userId;
	}*/
	
	public String getContent() {
		return content;
	}
	public Long getReplyUserId() {
		return replyUserId;
	}
	public void setReplyUserId(Long replyUserId) {
		this.replyUserId = replyUserId;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	
}
