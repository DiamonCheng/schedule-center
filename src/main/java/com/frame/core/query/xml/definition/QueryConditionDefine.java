package com.frame.core.query.xml.definition;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by Defferson.Cheng on 2016/12/10.
 */
public class QueryConditionDefine extends  QueryCondition {
    private String defaultValue="";
    private String inputType="TEXT";
    private String displayText;
    private String inputId;
    private String staticData;
    private Object parsedData;
    private String selectTextField;
    private String selectValueField="id";
    private Class<?> optionClass;
    @XmlAttribute
    public String getDefaultValue() {
        return defaultValue;
    }
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
    @XmlAttribute
    public String getInputType() {
        return inputType;
    }
    public void setInputType(String inputType) {
        this.inputType = inputType;
    }
    @XmlAttribute
    public String getDisplayText() {
        return displayText;
    }
    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }
    @XmlAttribute
    public String getInputId() {
        return inputId;
    }
    public void setInputId(String inputId) {
        this.inputId = inputId;
    }
    @XmlTransient
    public Object getParsedData() {
        return parsedData;
    }
    public void setParsedData(Object parsedData) {
        this.parsedData = parsedData;
    }
    public String getStaticData() {
        return staticData;
    }
    public void setStaticData(String staticData) {
        this.staticData = staticData;
    }
    @XmlAttribute
    public String getSelectTextField() {
        return selectTextField;
    }
    public void setSelectTextField(String selectTextField) {
        this.selectTextField = selectTextField;
    }
    @XmlAttribute
    public String getSelectValueField() {
        return selectValueField;
    }
    public void setSelectValueField(String selectValueField) {
        this.selectValueField = selectValueField;
    }
    @XmlAttribute
    @XmlJavaTypeAdapter(MappedClassEntry.class)
    public Class<?> getOptionClass() {
        return optionClass;
    }
    public void setOptionClass(Class<?> optionClass) {
        this.optionClass = optionClass;
    }
}
