package com.frame.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.frame.core.components.BaseEntity;
@Entity
@Table(name="td_student")
public class Student extends BaseEntity {
	private static final long serialVersionUID = -7140176779521414142L;
	private String name;
	private String no;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
}
