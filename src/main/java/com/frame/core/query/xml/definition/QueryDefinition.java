package com.frame.core.query.xml.definition;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class QueryDefinition {
	private List<MappedClassEntry> mappedClass;
	private String where;
	private List<SortEntry> sortBy=new ArrayList<SortEntry>();
	private String showIndex;
	private List<ColumnDefinition> columns;
	private int pageSize=10;
	private List<QueryConditionDefine> queryConditionDefines;
	public List<MappedClassEntry> getMappedClass() {
		return mappedClass;
	}
	public void setMappedClass(List<MappedClassEntry> mappedClass) {
		this.mappedClass = mappedClass;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
	@XmlElementWrapper(name="sort")
	@XmlElement(name="by")
	public List<SortEntry> getSortBy() {
		return sortBy;
	}
	public void setSortBy(List<SortEntry> sortBy) {
		this.sortBy = sortBy;
	}
	@XmlAttribute
	public String getShowIndex() {
		return showIndex;
	}
	public void setShowIndex(String showIndex) {
		this.showIndex = showIndex;
	}
	@XmlElementWrapper(name="columns")
	@XmlElement(name="column")
	public List<ColumnDefinition> getColumns() {
		return columns;
	}
	public void setColumns(List<ColumnDefinition> columns) {
		this.columns = columns;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	private List<String> requiredJsPath;
	private List<String> requiredCssPath;
	@XmlElement(name="path")
	@XmlElementWrapper(name = "js")
	public List<String> getRequiredJsPath() {
		return requiredJsPath;
	}
	public void setRequiredJsPath(List<String> requiredJsPath) {
		this.requiredJsPath = requiredJsPath;
	}
	@XmlElement(name="path")
	@XmlElementWrapper(name = "css")
	public List<String> getRequiredCssPath() {
		return requiredCssPath;
	}
	public void setRequiredCssPath(List<String> requiredCssPath) {
		this.requiredCssPath = requiredCssPath;
	}
	@XmlElement(name="condition")
	@XmlElementWrapper(name="searchDefinition")
	public List<QueryConditionDefine> getQueryConditionDefines() {
		return queryConditionDefines;
	}
	public void setQueryConditionDefines(List<QueryConditionDefine> queryConditionDefines) {
		this.queryConditionDefines = queryConditionDefines;
	}
	public List<QueryConditionDefine> cloneQueryConditionDefine() throws CloneNotSupportedException {
		ArrayList<QueryConditionDefine> res=new ArrayList<QueryConditionDefine>();
		for(QueryConditionDefine queryConditionDefine:queryConditionDefines){
			res.add((QueryConditionDefine) queryConditionDefine.clone());
		}
		return res;
	}

}
