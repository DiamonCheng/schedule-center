package com.frame.core.entity;

import com.frame.core.components.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Table(name="sys_role")
@Entity
public class RoleEntity extends BaseEntity {
	private static final long serialVersionUID = -1742694681527083908L;
	@Column(length=63)
	private String code;
	@Column(length=63)
	private String name;
	@Column(length=255)
	private String description;
	@ManyToMany(targetEntity=MenuEntity.class,fetch=FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name="null"))
	private Set<MenuEntity> alowMenus;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<MenuEntity> getAlowMenus() {
		return alowMenus;
	}
	public void setAlowMenus(Set<MenuEntity> alowMenus) {
		this.alowMenus = alowMenus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
