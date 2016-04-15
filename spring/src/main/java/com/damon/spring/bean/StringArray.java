package com.damon.spring.bean;

import java.util.Arrays;

/**
 * 测试CustomerStringToStringArrayConverter
 * Created by dongjun.wei on 16/3/21.
 */
public class StringArray {

    private String[] hobbies;

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "StringArray{" +
                "hobbies=" + Arrays.toString(hobbies) +
                '}';
    }
}
