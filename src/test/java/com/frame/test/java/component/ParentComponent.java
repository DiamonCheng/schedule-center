package com.frame.test.java.component;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by Defferson.Cheng on 2017/6/22.
 */
@XmlRootElement(namespace = "com.dc.frame.core.components.parentComponent")
public class ParentComponent extends XmlAdapter<Object,Component> implements Component {
    private String name;
    private Component child;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlJavaTypeAdapter(ParentComponent.class)
    public Component getChild() {
        return child;
    }

    public void setChild(Component child) {
        this.child = child;
    }

    @Override
    public Component unmarshal(Object v) throws Exception {
        return null;
    }

    @Override
    public Object marshal(Component v) throws Exception {
        return null;
    }
}
