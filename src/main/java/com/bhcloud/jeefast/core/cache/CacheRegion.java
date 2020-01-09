package com.bhcloud.jeefast.core.cache;

/**
 * 缓存Region
 *
 * @author: BaiHao
 * @date: 2020-01-09 下午 04:53
 */
public enum CacheRegion {

    USER_CACHE("userCache"), SYS_CACHE("sysCache");

    private final String region;

    CacheRegion(String region) {
        this.region = region;
    }

    public String value() {
        return region;
    }

}
