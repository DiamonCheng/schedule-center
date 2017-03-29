package com.frame.entity.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


import com.frame.core.components.BaseEntity;

/** 
* @author wowson
* @version create time：2017年3月10日 上午9:30:31 
* 类说明 ： 视频外链 实体
*/
@Entity
@Table(name="sys_video")
public class VideoEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2148492484514439872L;
	@Column(length=20)
	private Long createUserKey;
	@Column(length=20)
	private String videoName;  //原文件名
	private String savePath;    
	private String httpLink;
	private String type;    // 0 外链，1 上传
	@Type(type = "text")
	private String description;
	public Long getCreateUserKey() {
		return createUserKey;
	}
	public void setCreateUserKey(Long createUserKey) {
		this.createUserKey = createUserKey;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getHttpLink() {
		return httpLink;
	}
	public void setHttpLink(String httpLink) {
		this.httpLink = httpLink;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
