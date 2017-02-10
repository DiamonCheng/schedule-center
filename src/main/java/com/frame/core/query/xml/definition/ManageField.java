package com.frame.core.query.xml.definition;

import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * Created by Defferson.Cheng on 2017/1/6.
 */
public class ManageField extends QueryConditionDefine{
    //字段差不多，重用了。
    private boolean isEntity=false;
    @XmlTransient
    public boolean getIsEntity() {
        return isEntity;
    }

    public void setIsEntity(boolean entity) {
        isEntity = entity;
    }
    private Object value;
    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }
}
