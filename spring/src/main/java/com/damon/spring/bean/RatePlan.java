package com.damon.spring.bean;

import javax.xml.bind.annotation.*;

/**
 * XML转换测试bean
 * 注意，必须要有XmlRootElement注解，因为
 * Created by dongjun.wei on 16/3/18.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"id", "name"})
@XmlRootElement(name = "RatePlan")
public class RatePlan {

    @XmlElement(name = "ID")
    private String id;
    @XmlAttribute(name = "Name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RatePlan{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
