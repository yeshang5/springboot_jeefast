package com.bhcloud.jeefast.moudles.system.service;

import com.bhcloud.jeefast.moudles.system.entity.User;
import com.bhcloud.jeefast.moudles.system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 插入数据
     * @param user
     */
    public void insert(User user){
        userMapper.insert(user);
    }

    public User get(User user)
    {
        return userMapper.get(user);
    }
}
