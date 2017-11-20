package com.frame.core.entity;

import com.frame.core.components.BaseEntity;

import javax.persistence.*;
import java.util.Set;

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
	@ManyToMany(targetEntity=RoleEntity.class,fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name="null"))
	private Set<RoleEntity> roles;
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
	public Set<RoleEntity> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}
	
	
	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "UserEntity{" +
					   "userLoginVerification='" + userLoginVerification + '\'' +
					   ", userPassword='" + userPassword + '\'' +
					   ", nickName='" + nickName + '\'' +
					   ", roles=" + roles +
					   ", id=" + id +
					   ", version=" + version +
					   ", createDateTime=" + createDateTime +
					   ", updateDateTime=" + updateDateTime +
					   '}';
	}
}
