package com.damon.json.list;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by dongjun.wei on 16/2/23.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
@JsonSubTypes({ @JsonSubTypes.Type(value = Lion.class, name = "lion"), @JsonSubTypes.Type(value = Elephant.class, name = "elephant") })
public class Animal {

    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
