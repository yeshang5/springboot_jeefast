package com.bhcloud.jeefast.common.utils;

import cn.hutool.core.util.StrUtil;
import com.bhcloud.jeefast.core.cache.CacheRegion;
import net.oschina.j2cache.CacheObject;
import net.oschina.j2cache.J2Cache;

import org.springframework.util.StringUtils;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    /**
     * 从缓存中移除
     *
     * @param region
     * @param key
     */
    public static void remove(String region, String key) {
        J2Cache.getChannel().evict(region, key);
    }

    /**
     * 获取缓存
     * @param region
     * @param key
     * @return
     */
    public static Object get(String region, String key)
    {
        CacheObject object=J2Cache.getChannel().get(region,key);
        return object==null? null: object.getValue();
    }

    /**
     * 获取region -> keys
     *
     * @param region
     * @return
     */
    public static Collection<String> getKeys(String region) {
        return J2Cache.getChannel().keys(region);
    }

    /**
     * 获取region下的所有数据
     *
     * @param region
     * @return
     */
    public static List getAll(String region) {
        if (StrUtil.isBlank(region)) {
            return null;
        }
        List allList = new ArrayList();
        Collection<String> keyList = getKeys(region);
        for (Object key : keyList) {
            CacheObject object = J2Cache.getChannel().get(region, key.toString());
            allList.add(object == null ? null : object.getValue());
        }
        return allList;
    }
}
