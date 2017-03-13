package com.frame.entity.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.frame.core.components.BaseEntity;

/** 
* @author wowson
* @version create time：2017年3月10日 上午9:30:12 
* 类说明 ：
*/
@Entity
@Table(name="sys_img_group")
public class ImgGroupEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8068751547335026680L;
	@Column(length=20)
	private Long createUserKey;
	private String imgGroupName;
	public Long getCreateUserKey() {
		return createUserKey;
	}
	public void setCreateUserKey(Long createUserKey) {
		this.createUserKey = createUserKey;
	}
	public String getImgGroupName() {
		return imgGroupName;
	}
	public void setImgGroupName(String imgGroupName) {
		this.imgGroupName = imgGroupName;
	}
	
}
