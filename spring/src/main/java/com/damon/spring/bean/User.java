package com.damon.spring.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户实体bean
 * Created by dongjun.wei on 16/3/16.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User {

    private String name;

    private String password;

    private boolean married;

    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    //注意可能出现时差，这里加上了时区。这个注解需要添加joda time依赖。
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    public Date getBirthday() {
        return birthday;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", married=" + married +
                ", birthday=" + birthday +
                '}';
    }
}
