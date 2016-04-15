package com.damon.util.config;

import com.google.common.collect.Maps;
import com.typesafe.config.Config;

import java.util.Map;

/**
 * 配置工厂类，会自动加载classpath目录下的config文件夹的所有文件 配置文件使用简化后的json格式
 * 具体请参见 https://github.com/typesafehub/config
 * Created by dongjun.wei on 16/2/22.
 */
public final class ConfigFactory {
    /*** 缓存map,默认使用lazy load的模式 */
    private static Map<String, Config> cachedConfigMap = Maps.newHashMap();

    /***
     * 获取一个配置 查看缓存是否加载，如果没有加载就执行加载逻辑
     *
     * @param configName 放在config目录下的文件名，不包含后缀
     */
    public static synchronized Config getConfig(String configName) {
        Config config = cachedConfigMap.get(configName);
        if (config != null) {
            return config;
        }
        config = com.typesafe.config.ConfigFactory.load("config/" + configName);
        cachedConfigMap.put(configName, config);
        return config;
    }

    public static Config getGlobalConfig() {
        return getConfig("global");
    }

}
