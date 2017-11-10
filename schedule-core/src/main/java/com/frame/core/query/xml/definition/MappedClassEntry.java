package com.frame.core.query.xml.definition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.List;

public class MappedClassEntry extends XmlAdapter<String,Class<? extends Serializable>>{
	private static final Logger LOGGER= LoggerFactory.getLogger(MappedClassEntry.class);
	
	private Class<? extends Serializable> mappedClass;
	private String alias;
	private List<JoinEntry> join;
	
	public MappedClassEntry(){}
	public MappedClassEntry(Class<? extends Serializable> mappedClass, String alias) {
		this.mappedClass = mappedClass;
		this.alias = alias;
	}
	@XmlAttribute
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	@XmlAttribute
	@XmlJavaTypeAdapter(value=MappedClassEntry.class)
	public Class<? extends Serializable> getMappedClass() {
		return mappedClass;
	}
	public void setMappedClass(Class<? extends Serializable> mappedClass) {
		this.mappedClass = mappedClass;
	}
	@Override
	public String marshal(Class<? extends Serializable> v) throws Exception {
		return v.getName();
	}
	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends Serializable> unmarshal(String v) throws Exception {
		try {
			return (Class<? extends Serializable>) Class.forName(v);
		} catch (ClassNotFoundException e) {
			LOGGER.error("An exception occurred when lock for class name:"+v,e);
			throw e;
		}
	}
	public List<JoinEntry> getJoin() {
		return join;
	}
	public void setJoin(List<JoinEntry> join) {
		this.join = join;
	}
}
