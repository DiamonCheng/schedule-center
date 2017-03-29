package com.frame.entity.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.frame.core.components.BaseEntity;
import com.frame.core.entity.UserEntity;

/** 
* @author wowson
* @version create time：2017年3月21日 下午7:15:23 
* 类说明 ：
*/
@Entity
@Table(name="sys_message")
public class MessageEntity extends BaseEntity{
	
	
	@Type(type = "text")
	private String content;
	@Column(length=20)
	private Long albumId;
	 /*
     * cascade：为级联操作，里面有级联保存，级联删除等，all为所有 
     * fetch：加载类型，有lazy和eager二种，
     *   eager为急加载，意为立即加载，在类加载时就加载，lazy为懒加载，第一次调用的时候再加载，由于数据量太大，onetomany一般为lazy
     * mappedBy：这个为manytoone中的对象名，
     * Set<ImgEntity>：这个类型有两种，一种为list另一种为set
     */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(foreignKey = @ForeignKey(name="null"))
	private UserEntity user;
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	public Long getAlbumId() {
		return albumId;
	}
	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	/*public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}*/
	 
	

}
