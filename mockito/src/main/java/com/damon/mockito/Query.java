package com.damon.mockito;

import java.util.Map;

/**
 * Created by dongjun.wei on 16/3/10.
 */
public interface Query {

    String getContent(String key);

    String query(String url, Map<String, String> param);
}
