package com.bhcloud.jeefast.common.utils;

import com.bhcloud.jeefast.core.cache.CacheRegion;
import net.oschina.j2cache.J2Cache;

/**
 * 缓存工具类
 * @author: BaiHao
 * @date: 2020-01-09 下午 04:40
 */
public class CacheUtils {

    /**
     * 写入缓存
     * @param region
     * @param key
     * @param value
     */
    public static void save(String region, String key, Object value)
    {
        J2Cache.getChannel().set(region,key,value);
    }
}
