package com.frame.entity;

import java.util.List;

import javax.persistence.*;

import com.frame.core.components.BaseEntity;
@Table(name="sys_menu")
@Entity
public class MenuEntity extends BaseEntity {
	private static final long serialVersionUID = 2283028973673917967L;
	private String displayName;
	private String requestURI;
	private boolean hidden;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name="null"))
	private MenuEntity parent;
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="parent_id",foreignKey =  @ForeignKey(name="null"))
	private List<MenuEntity> children;
	public List<MenuEntity> getChildren() {
		return children;
	}
	public void setChildren(List<MenuEntity> children) {
		this.children = children;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getRequestURI() {
		return requestURI;
	}
	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}
	public MenuEntity getParent() {
		return parent;
	}
	public void setParent(MenuEntity parent) {
		this.parent = parent;
	}
	public boolean getHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
}
