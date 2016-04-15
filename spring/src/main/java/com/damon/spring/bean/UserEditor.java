package com.damon.spring.bean;

import com.google.common.base.Splitter;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * 自定义的User转换器
 * 请求参数是String，使用setAsText方法将其转化为User
 * 如果这个转换器放在跟带转换的实体bean（这里的User）在同一个包下，
 * 且其beanNameEditor命名（这里的UserEditor），那么就不需要特别的注册
 * 否认需要注册，本系统是在Controller中使用@InitBinder注册的
 * Created by dongjun.wei on 16/3/17.
 */
public class UserEditor extends PropertyEditorSupport {

    public static final Logger logger = LoggerFactory.getLogger(UserEditor.class);

    public static final Splitter SPLITTER = Splitter.on("_").omitEmptyStrings();

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        logger.info("UserEditor text:{}", text);
        List<String> list = SPLITTER.splitToList(text);
        if (CollectionUtils.isNotEmpty(list) && list.size() == 2) {
            User user = new User();
            user.setName(list.get(0));
            user.setPassword(list.get(1));
            user.setBirthday(new Date());
            user.setMarried(true);
            setValue(user);
        } else {
            setValue(null);
        }
    }


}
