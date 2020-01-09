package com.bhcloud.jeefast.moudles.system.controller;

import com.bhcloud.jeefast.common.utils.CacheUtils;
import com.bhcloud.jeefast.core.cache.CacheRegion;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("test")
    public String test()
    {
        CacheUtils.save(CacheRegion.SYS_CACHE.value(),"test","123465");
        return "test success";
    }
}
