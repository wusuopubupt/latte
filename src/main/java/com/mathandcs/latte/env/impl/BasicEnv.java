package com.mathandcs.latte.env.impl;

import com.mathandcs.latte.env.Environment;

import java.util.Map;

/**
 * Created by dash wang on 2/24/18.
 */
public class BasicEnv implements Environment {

    Map<Object, Object> envMap;

    public BasicEnv(Map<Object, Object> envMap) {
        this.envMap = envMap;
    }

    @Override
    public void put(Object key, Object val) {
        envMap.put(key, val);
    }

    @Override
    public Object get(Object key) {
        return envMap.get(key);
    }
}
