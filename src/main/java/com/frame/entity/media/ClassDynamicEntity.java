package com.frame.entity.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.frame.core.components.BaseEntity;

/** 
* @author wowson
* @version create time：2017年3月29日 上午9:09:39 
* 类说明 ：
*/
@Entity
@Table(name="sys_class_dynamic")
public class ClassDynamicEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8186662361587275110L;
	
	@Column(name="user_id")
	private Long userId;
	private  String  title;
	@Type(type="text")
	private String content;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}
