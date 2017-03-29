package com.frame.entity.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.frame.core.components.BaseEntity;

/** 
* @author wowson
* @version create time：2017年3月10日 上午9:30:49 
* 类说明 ： 视频上传
*/
@Entity
@Table(name="sys_video_upload")
public class VideoUploadEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6836295695354377387L;
	@Column(length=20)
	private Long createUserKey;
	private String videoName;  //原文件名
	private String videoNewName;  //新文件名
	private String savePath;    //用户界面显示
	private String httpLink;    //管理员界面显示
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
	public String getVideoNewName() {
		return videoNewName;
	}
	public void setVideoNewName(String videoNewName) {
		this.videoNewName = videoNewName;
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
