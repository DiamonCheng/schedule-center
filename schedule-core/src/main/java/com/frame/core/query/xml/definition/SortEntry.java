package com.frame.core.query.xml.definition;

import javax.xml.bind.annotation.XmlAttribute;

public class SortEntry {
	private String field;
	private String fromAlias;
	private String order;
	@XmlAttribute
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	@XmlAttribute
	public String getFromAlias() {
		return fromAlias;
	}
	public void setFromAlias(String fromAlias) {
		this.fromAlias = fromAlias;
	}
	@XmlAttribute
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
}
