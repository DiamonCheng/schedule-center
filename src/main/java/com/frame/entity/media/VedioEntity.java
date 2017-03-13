package com.frame.entity.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.frame.core.components.BaseEntity;

/** 
* @author wowson
* @version create time：2017年3月10日 上午9:30:31 
* 类说明 ：
*/
@Entity
@Table(name="sys_vedio")
public class VedioEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2148492484514439872L;
	@Column(length=20)
	private Long createUserKey;
	@Column(length=20)
	private Long vedioGroupKey;
	public Long getCreateUserKey() {
		return createUserKey;
	}
	public void setCreateUserKey(Long createUserKey) {
		this.createUserKey = createUserKey;
	}
	public Long getVedioGroupKey() {
		return vedioGroupKey;
	}
	public void setVedioGroupKey(Long vedioGroupKey) {
		this.vedioGroupKey = vedioGroupKey;
	}
	public String getVedioName() {
		return vedioName;
	}
	public void setVedioName(String vedioName) {
		this.vedioName = vedioName;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	private String vedioName;
	private String savePath;
	
}
