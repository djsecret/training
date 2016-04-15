package com.damon.mockito.impl;

import com.damon.mockito.Query;

import java.util.Map;

/**
 * Created by dongjun.wei on 16/3/10.
 */
public class QueryImpl implements Query {

    @Override
    public String getContent(String key) {
        return "content:" + key;
    }

    @Override
    public String query(String url, Map<String, String> param) {
        return "query:" + url;
    }
}
