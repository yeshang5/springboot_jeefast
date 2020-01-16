package com.bhcloud.jeefast.moudles.system.entity;

import com.bhcloud.jeefast.common.entity.DataEntity;
import com.bhcloud.jeefast.common.utils.SpringContextHolder;
import com.bhcloud.jeefast.core.config.Global;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User extends DataEntity<User> {
    private static final long serialVersionUID = 1L;
    private Office company;	// 归属单位
    private Office office;	// 归属部门
    private String loginName;// 登录名
    private String password;// 密码
    private String no;		// 工号
    private String name;	// 姓名
    private String email;	// 邮箱
    private String phone;	// 电话
    private String mobile;	// 手机
    private String loginIp;	// 最后登陆IP
    private Date loginDate;	// 最后登陆日期
    private String loginFlag;	// 是否允许登陆
    private String photo;	// 头像
    private String qrCode;	//二维码
    private String oldLoginName;// 原登录名
    private String newPassword;	// 新密码
    private String sign;//签名

    private String oldLoginIp;	// 上次登陆IP
    private Date oldLoginDate;	// 上次登陆日期

    private Role role;	// 根据角色查询用户条件

    private List<Role> roleList = Lists.newArrayList(); // 拥有角色列表

    private String roleEnNames;

    public User() {
        super();
        this.loginFlag = Global.YES;
    }

    public String getPhoto() {
        if("".equals(photo)){
            return SpringContextHolder.getStatic()+"/common/images/flat-avatar.png";
        }
        return photo;
    }
}
