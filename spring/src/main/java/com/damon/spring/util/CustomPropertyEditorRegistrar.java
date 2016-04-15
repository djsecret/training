package com.damon.spring.util;

import com.damon.spring.bean.User;
import com.damon.spring.bean.UserEditor;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 在MVC中没有见到效果= =
 * 最后用@InitBinder实现的
 * Created by dongjun.wei on 16/3/17.
 */
public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        //这里是想注册Spring自带的CustomDateEditor，将String类型转化为Date类型，可是没有成功。。
        //还是在属性的set方法上加@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")吧
        //还有@InitBinder注解
        registry.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
        registry.registerCustomEditor(User.class, new UserEditor());
    }
}
