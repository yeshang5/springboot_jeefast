package com.bhcloud.jeefast.moudles.user.entity;

import com.bhcloud.jeefast.common.entity.BaseEntity;
import lombok.Data;

@Data
public class User extends BaseEntity<User> {
    private String id;          //id
    private String loginName;      //登录名
    private String name;        //用户名
    private String password;    //密码
    private String tel;     //电话
}
