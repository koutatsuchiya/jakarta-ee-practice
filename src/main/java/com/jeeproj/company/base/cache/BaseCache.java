package com.jeeproj.company.base.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Getter;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Getter
public abstract class BaseCache<E> {
    private Cache<String, Map<Long, E>> cache;

    protected BaseCache(long cacheSize, long expiredTime, TimeUnit unit) {
        cache = Caffeine.newBuilder()
                .maximumSize(cacheSize)
                .expireAfterAccess(expiredTime, unit)
                .build();
    }
}
