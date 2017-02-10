package com.frame.core.query.xml.definition;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import java.lang.reflect.Method;

/**
 * Created by Defferson.Cheng on 2017/1/3.
 */
public class Delete {
    private String beforeDelete;
    private Method beforeDeleteMethod;
    private Integer injectIndex;
    @XmlAttribute
    public String getBeforeDelete() {
        return beforeDelete;
    }

    public void setBeforeDelete(String beforeDelete) {
        this.beforeDelete = beforeDelete;
    }
    @XmlTransient
    public Method getBeforeDeleteMethod() {
        return beforeDeleteMethod;
    }

    public void setBeforeDeleteMethod(Method beforeDeleteMethod) {
        this.beforeDeleteMethod = beforeDeleteMethod;
    }
    @XmlTransient
    public Integer getInjectIndex() {
        return injectIndex;
    }

    public void setInjectIndex(Integer injectIndex) {
        this.injectIndex = injectIndex;
    }
}
