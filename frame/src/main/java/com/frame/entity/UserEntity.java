package com.frame.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.frame.core.components.BaseEntity;
@Entity
@Table(name="sys_user",uniqueConstraints=@UniqueConstraint(columnNames = { "userLoginVerification" }))
public class UserEntity extends BaseEntity {
	private static final long serialVersionUID = -6918259654521503874L;
	@Column(length=63)
	private String userLoginVerification;
	@Column(length=63)
	private String userPassword;
	@Column(length=63)
	private String nickName;
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserLoginVerification() {
		return userLoginVerification;
	}
	public void setUserLoginVerification(String userLoginVerification) {
		this.userLoginVerification = userLoginVerification;
	}
	
}
