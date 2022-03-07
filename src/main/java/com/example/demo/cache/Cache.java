package com.example.demo.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private static final Map<String, Object> cache = new ConcurrentHashMap<>();

    public static void put(String key, Object value) {
        cache.put(key, value);
    }

    public static Object get(String key) {
        return cache.get(key);
    }
}
