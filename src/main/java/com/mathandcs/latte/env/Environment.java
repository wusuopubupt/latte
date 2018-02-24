package com.mathandcs.latte.env;

/**
 * Created by dash wang on 8/1/17.
 */
public interface Environment {

    void put(Object key, Object val);

    Object get(Object key);
}
