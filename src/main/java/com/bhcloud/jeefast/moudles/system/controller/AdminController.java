package com.bhcloud.jeefast.moudles.system.controller;

import com.bhcloud.jeefast.common.utils.CacheUtils;
import com.bhcloud.jeefast.core.cache.CacheRegion;
import com.bhcloud.jeefast.moudles.user.entity.User;
import com.bhcloud.jeefast.moudles.user.service.UserService;
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

    @GetMapping("test")
    public String test()
    {
        //TODO 解决J2Cache报错的问题
       /* CacheUtils.save(CacheRegion.SYS_CACHE.value(),"test","123465");*/
        return "test success";
    }

    @GetMapping("insertUser")
    public User insertUser(User user)
    {
        if (user!=null)
        {
            user.setId("123456");
            userService.insert(user);
        }
        return user;
    }

    @GetMapping("getUser")
    public User getUser(User user)
    {
        if (StringUtils.isEmpty(user.getId()))
        {
            user=new User();

        }else {
            user=userService.get(user);
        }
        return user;
    }
}
