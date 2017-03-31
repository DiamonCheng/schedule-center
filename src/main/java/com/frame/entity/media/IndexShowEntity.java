package com.frame.entity.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.frame.core.components.BaseEntity;

/** 
* @author wowson
* @version create time：2017年3月30日 上午9:45:00 
* 类说明 ：
*/
@Entity
@Table(name="sys_index_show")
public class IndexShowEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String title;
	@Type(type="text")
	private String content;
	@Column(length=2)
	private Long  status;  // 0 forbidden ， 1 allowed
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
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status2) {
		this.status = status2;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
