package com.idg.common.cache;


import org.springframework.cache.Cache;

/**
 * Created by yehao on 16/7/25.
 * 缓存操作类
 */
public class DemoCache {

    private Cache cache;

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public Object get(Object key) {
        Cache.ValueWrapper vw = this.cache.get(key);
        if (vw == null) {
            return null;
        } else {
            return vw.get();
        }
    }

    public void put(Object key, Object value) {
        this.cache.put(key, value);
    }
}
