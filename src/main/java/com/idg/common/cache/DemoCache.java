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
            if ("time".equals(key)) {
                long currentTime = System.currentTimeMillis();
                put("time", currentTime);
                return currentTime;
            }
            return null;
        } else {
            return vw.get();
        }
    }

    /**
     * 获取字符串缓存
     *
     * @param key 缓存的key
     * @return 字符串缓存
     */
    public String getString(String key) {
        Object o = get(key);
        if (o == null) {
            return null;
        }
        return String.valueOf(o);
    }

    public void put(Object key, Object value) {
        this.cache.put(key, value);
    }
}
