package com.frame.test.java.component;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Defferson.Cheng on 2017/6/22.
 */
public class ChildComponent implements Component {
    private String attr1;

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }
}
