package com.frame.core.query.xml.definition;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Defferson.Cheng on 2017/1/8.
 */
public class Manage {
    private List<ManageField> field;

    public List<ManageField> getField() {
        return field;
    }
    public void setField(List<ManageField> field) {
        this.field = field;
    }
    public List<ManageField> cloneFields() throws CloneNotSupportedException {
        ArrayList<ManageField> res=new ArrayList<ManageField>(field.size());
        for(ManageField manageField:field){
            res.add((ManageField) manageField.clone());
        }
        return res;
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
}
