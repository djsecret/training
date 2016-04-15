package com.damon.util.config;

import com.damon.constant.ConfigConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * 使用原始的load方法，不支持动态加载
 * Created by dongjun.wei on 16/2/22.
 */
public class SimpleLocalConfig {
    public static final Logger logger = LoggerFactory.getLogger(SimpleLocalConfig.class);

    private static Properties PROP = new Properties();

    static {
        try {
            PROP.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(ConfigConstant.LOCAL_CONFIG));
        } catch (IOException e) {
            logger.error("load config failed.", e);
        }
    }

    public static String getProperty(String key) {
        return PROP.getProperty(key);
    }

}
