package com.frame.model.vo.media;

import java.util.Date;

import com.frame.entity.media.ClassDynamicEntity;

/** 
* @author wowson
* @version create time：2017年3月30日 下午7:34:24 
* 类说明 ：
*/
public class ClassDynamicVO extends ClassDynamicEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long classDynamicId;
	private String content;
	private String title;
	private Date  updateDateTime;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public Long getClassDynamicId() {
		return classDynamicId;
	}
	public void setClassDynamicId(Long classDynamicId) {
		this.classDynamicId = classDynamicId;
	}
	
}
