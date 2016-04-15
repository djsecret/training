package com.damon.json.list;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dongjun.wei on 16/2/23.
 */
public class Lion extends Animal {

    @JsonCreator
    public Lion(@JsonProperty("name") String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Lion:" + name;
    }
}
