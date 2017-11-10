package com.frame.core.query.xml.definition;

import javax.xml.bind.annotation.XmlTransient;

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
    
    
}
