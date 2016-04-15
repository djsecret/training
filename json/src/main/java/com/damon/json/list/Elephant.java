package com.damon.json.list;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dongjun.wei on 16/2/23.
 */
public class Elephant extends Animal {

    @JsonCreator
    public Elephant(@JsonProperty("name") String name) {
        super.name = name;
    }

    @Override
    public String toString() {
        return "Elephant:" + name;
    }
}
