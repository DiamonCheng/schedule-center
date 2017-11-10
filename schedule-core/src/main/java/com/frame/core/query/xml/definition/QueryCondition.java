package com.frame.core.query.xml.definition;

import com.frame.core.components.GsonFactory;
import com.google.gson.Gson;
import org.springframework.util.StringUtils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

public class QueryCondition implements Cloneable{
	private static final Gson gson=GsonFactory.buildDefaultGson();
	private String field;
	private String alias;
	private Class<?> type;
	private Object value;
	private String operator;
	private String extra;
	private boolean nullable=true;
	public Object parsedValue(){
		if (!StringUtils.isEmpty(this.value)) return gson.fromJson("'"+value.toString().replaceAll("'","\\'")+"'", type);
		else return null;
	}
	/*public static void main(String[] args) {
		String json="{field:'field',alias:'alias',type:'java.util.Date',value:'2013-2-2 12:12:12',operator:'LIKE'}";
		QueryCondition q=GsonFactory.buildDefaultGson().fromJson(json, QueryCondition.class);
		System.out.println(q.parseValue().getValue());
	}*/
	@XmlAttribute
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	@XmlAttribute
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	@XmlAttribute
	public Class<?> getType() {
		return type;
	}
	public void setType(Class<?> type) {
		this.type = type;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	@XmlTransient
	public Object getValue() {
//		if(!isParsed) this.parseValue();
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	@XmlAttribute
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public boolean equalsField(QueryCondition q){
		if (q==null) return false;
		boolean isAliasEqual=
				(this.getAlias()==null||"".equals(this.getAlias()))&&(q.getAlias()==null||"".equals(q.getAlias()))
						|| this.getAlias()!=null&& this.getAlias().equals(q.getAlias());
		boolean isFieldEqual= this.getField().equals(q.getField());
		return isAliasEqual&&isFieldEqual&&this.getOperator().equals(q.getOperator());
	}
	@XmlAttribute
	public boolean getNullable() {
		return nullable;
	}
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
