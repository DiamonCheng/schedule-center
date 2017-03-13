package com.frame.entity.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.frame.core.components.BaseEntity;

/** 
* @author wowson
* @version create time：2017年3月10日 上午9:30:49 
* 类说明 ：
*/
@Entity
@Table(name="sys_vedio_group")
public class VedioGroupEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6836295695354377387L;
	@Column(length=20)
	private Long createUserKey;
	@Column(length = 63)
	private String imgGroupNAmee;
	public Long getCreateUserKey() {
		return createUserKey;
	}
	public void setCreateUserKey(Long createUserKey) {
		this.createUserKey = createUserKey;
	}
	public String getImgGroupNAmee() {
		return imgGroupNAmee;
	}
	public void setImgGroupNAmee(String imgGroupNAmee) {
		this.imgGroupNAmee = imgGroupNAmee;
	}
}
