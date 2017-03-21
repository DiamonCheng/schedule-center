package com.frame.entity.media;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	private String previewPic;
	private Long countImg;
	 /*
     * cascade：为级联操作，里面有级联保存，级联删除等，all为所有 
     * fetch：加载类型，有lazy和eager二种，
     *   eager为急加载，意为立即加载，在类加载时就加载，lazy为懒加载，第一次调用的时候再加载，由于数据量太大，onetomany一般为lazy
     * mappedBy：这个为manytoone中的对象名，
     * Set<ImgEntity>：这个类型有两种，一种为list另一种为set
     */
	/*@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="imgGroupKey",foreignKey =  @ForeignKey(name="null"))
	private List<ImgEntity> imgEntity;
	
	 public List<ImgEntity> getImgEntity() {
		return imgEntity;
	}
	void setImgEntity(List<ImgEntity> imgEntity) {
		this.imgEntity = imgEntity;
	}*/
	
	public Long getCreateUserKey() {
		return createUserKey;
	}
	public Long getCountImg() {
		return countImg;
	}
	public void setCountImg(Long countImg) {
		this.countImg = countImg;
	}
	public String getPreviewPic() {
		return previewPic;
	}
	public void setPreviewPic(String previewPic) {
		this.previewPic = previewPic;
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
