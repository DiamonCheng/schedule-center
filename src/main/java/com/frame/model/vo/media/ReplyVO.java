package com.frame.model.vo.media;

import java.util.Date;

import com.frame.entity.media.ReplyEntity;

/** 
* @author wowson
* @version create time：2017年3月22日 下午10:02:39 
* 类说明 ：
*/
public class ReplyVO  extends ReplyEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String replyUserName;
	private String replyContent;
	private Date replyTime;
	public String getReplyUserName() {
		return replyUserName;
	}
	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	
}
