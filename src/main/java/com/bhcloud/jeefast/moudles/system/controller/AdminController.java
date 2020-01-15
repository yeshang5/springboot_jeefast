package com.bhcloud.jeefast.moudles.system.controller;

import com.bhcloud.jeefast.common.utils.CacheUtils;
import com.bhcloud.jeefast.core.cache.CacheRegion;

import com.bhcloud.jeefast.moudles.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试类控制器
 *
 * @author: BaiHao
 * @date: 2020-01-09 下午 05:25
 */
@RestController
@RequestMapping("${adminPath}")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("insertCache")
    public String insertCache(String region,String key,String value)
    {
        //TODO 解决J2Cache报错的问题
        CacheUtils.save(region,key,value);
        return "写入缓存成功";
    }

    @GetMapping("getCache")
    public String getCache(String region,String key)
    {
        //TODO 解决J2Cache报错的问题
        Object result= CacheUtils.get(region,key);
        return result==null?"缓存已过期":result.toString();
    }

    @GetMapping("removeCache")
    public String removeCache(String region,String key)
    {
        //TODO 解决J2Cache报错的问题
        CacheUtils.remove(region,key);
        return "清除缓存成功";
    }
}
