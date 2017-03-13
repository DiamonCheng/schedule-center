package com.frame.entity.media;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.frame.core.components.BaseEntity;

/** 
* @author wowson
* @version create time：2017年3月10日 上午9:29:55 
* 类说明 ：图片实体
*/
@Entity
@Table(name="sys_img")
public class ImgEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8121453121086711618L;
	@Column(length=63)
	private String imgName;
	@Column(length=20)
	private Long createUserKey;
	@Column(length=128)
	private String  savePath;
	@Column(length=20)
	private Long imgGroupKey;
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public int getImg_area() {
		return img_area;
	}
	public void setImg_area(int img_area) {
		this.img_area = img_area;
	}
	private int img_area;
	public Long getCreateUserKey() {
		return createUserKey;
	}
	public void setCreateUserKey(Long createUserKey) {
		this.createUserKey = createUserKey;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public Long getImgGroupKey() {
		return imgGroupKey;
	}
	public void setImgGroupKey(Long imgGroupKey) {
		this.imgGroupKey = imgGroupKey;
	}
	
}
