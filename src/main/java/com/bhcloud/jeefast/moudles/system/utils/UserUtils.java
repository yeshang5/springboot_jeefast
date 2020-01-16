package com.bhcloud.jeefast.moudles.system.utils;

import com.bhcloud.jeefast.moudles.system.entity.Area;
import com.bhcloud.jeefast.moudles.system.entity.DataRule;
import com.bhcloud.jeefast.moudles.system.entity.Menu;
import com.bhcloud.jeefast.moudles.system.entity.User;
import com.google.common.collect.Lists;
import com.jeefw.common.sms.SMSUtils;
import com.jeefw.common.utils.CacheUtils;
import com.jeefw.common.utils.SpringContextHolder;
import com.jeefw.core.cache.Region;
import com.jeefw.modules.basedata.entity.HiddenPointInfo;
import com.jeefw.modules.basedata.entity.card.PreventionCard;
import com.jeefw.modules.basedata.entity.evacuation.EmergencyEvacuation;
import com.jeefw.modules.basedata.entity.govern.GovernProject;
import com.jeefw.modules.basedata.entity.preventionplan.DisasterPrevention;
import com.jeefw.modules.basedata.entity.professionalmonitor.ProfessionalMonitor;
import com.jeefw.modules.basedata.entity.refugecard.RefugeCard;
import com.jeefw.modules.basedata.entity.relocate.EmergencyShelter;
import com.jeefw.modules.basedata.entity.relocate.RelocationAvoidance;
import com.jeefw.modules.basedata.entity.research.EmergencyResearch;
import com.jeefw.modules.basedata.entity.simplemonitor.SimpleMonitorPoint;
import com.jeefw.modules.basedata.service.HiddenPointInfoService;
import com.jeefw.modules.basedata.service.card.PreventionCardService;
import com.jeefw.modules.basedata.service.evacuation.EmergencyEvacuationService;
import com.jeefw.modules.basedata.service.govern.GovernProjectService;
import com.jeefw.modules.basedata.service.preventionplan.DisasterPreventionService;
import com.jeefw.modules.basedata.service.professionalmonitor.ProfessionalMonitorService;
import com.jeefw.modules.basedata.service.refugecard.RefugeCardService;
import com.jeefw.modules.basedata.service.relocate.EmergencyShelterService;
import com.jeefw.modules.basedata.service.relocate.RelocationAvoidanceService;
import com.jeefw.modules.basedata.service.research.EmergencyResearchService;
import com.jeefw.modules.basedata.service.simplemonitor.SimpleMonitorPointService;
import com.jeefw.modules.duty.entity.up.*;
import com.jeefw.modules.duty.service.up.*;
import com.jeefw.modules.heritage.entity.ProtectHeritageProgress;
import com.jeefw.modules.heritage.service.ProtectHeritageProgressService;
import com.jeefw.modules.mine.entity.GovernMineProgress;
import com.jeefw.modules.mine.service.GovernMineProgressService;
import com.jeefw.modules.sys.entity.*;
import com.jeefw.modules.sys.mapper.*;
import com.jeefw.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.jeefw.modules.sys.service.AreaService;
import com.jeefw.modules.sys.service.OfficeService;
import com.jeefw.modules.sys.service.RoleService;
import com.jeefw.modules.training.entity.DrillStatistic;
import com.jeefw.modules.training.entity.TrainingStatistic;
import com.jeefw.modules.training.service.DrillStatisticService;
import com.jeefw.modules.training.service.TrainingStatisticService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户工具类
 *
 * @author jeefw
 * @version 2016-12-05
 */
public class UserUtils {

    /**
     * 根据ID获取用户
     *
     * @param id
     * @return 取不到返回null
     */
    public static User get(String id) {
        User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
        if (user == null) {
            user = userMapper.get(id);
            if (user == null) {
                return null;
            }
            user.setRoleList(roleMapper.findList(new Role(user)));
            CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
            CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
        }
        return user;
    }

    /**
     * 根据登录名获取用户
     *
     * @param loginName
     * @return 取不到返回null
     */
    public static User getByLoginName(String loginName) {
        User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);
        if (user == null) {
            user = userMapper.getByLoginName(new User(null, loginName));
            if (user == null) {
                return null;
            }
            user.setRoleList(roleMapper.findList(new Role(user)));
            CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
            CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
        }
        return user;
    }

    /**
     * 清除当前用户缓存
     */
    public static void clearCache() {
        UserSessionUtil.removeCache(CACHE_DATA_RULE_LIST);
        UserSessionUtil.removeCache(CACHE_MENU_LIST);
        UserUtils.clearCache(getUser());
    }

    /**
     * 清除指定用户缓存
     *
     * @param user
     */
    public static void clearCache(User user) {
        CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
        CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName());
        CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getOldLoginName());
        if (user.getOffice() != null && user.getOffice().getId() != null) {
            CacheUtils.remove(USER_CACHE, USER_CACHE_LIST_BY_OFFICE_ID_ + user.getOffice().getId());
        }
        //cached from CacheServiceInterceptor
        CacheUtils.getKeys(USER_CACHE).stream().filter(a -> (a.startsWith(USER_CACHE_LOGIN_NAME_ + user.getLoginName())
                || a.startsWith(USER_CACHE_LOGIN_NAME_ + user.getOldLoginName()))).forEach(key -> {
            CacheUtils.remove(USER_CACHE, key);
        });
    }

    /**
     * 获取当前用户
     *
     * @return 取不到返回 new User()
     */
    public static User getUser() {
        Principal principal = getPrincipal();
        if (principal != null) {
            User user = get(principal.getId());
            if (user != null) {
                return user;
            }
            return new User();
        }
        // 如果没有登录，则返回实例化空的User对象。
        return new User();
    }

    /**
     * 获取当前用户角色列表
     *
     * @return
     */
    public static List<Role> getRoleList() {
        return roleService.findList(new Role());
    }

    /**
     * 获取当前用户授权菜单
     *
     * @return
     */
    public static List<Menu> getMenuList() {
        @SuppressWarnings("unchecked")
        List<Menu> menuList = (List<Menu>) UserSessionUtil.getCache(CACHE_MENU_LIST);
        if (menuList == null) {
            User user = getUser();
            if (user.isAdmin()) {
                menuList = menuMapper.findAllList(new Menu());
            } else {
                Menu m = new Menu();
                m.setUserId(user.getId());
                menuList = menuMapper.findByUserId(m);
            }
            UserSessionUtil.putCache(CACHE_MENU_LIST, menuList);
        }
        return menuList;
    }

    /**
     * 获取当前用户授权数据权限
     *
     * @return
     */
    public static List<DataRule> getDataRuleList(User user) {
        @SuppressWarnings("unchecked")
        List<DataRule> dataRuleList = (List<DataRule>) UserSessionUtil.getCache(CACHE_DATA_RULE_LIST);
        if (dataRuleList == null) {
            if (user.isAdmin()) {
                dataRuleList = Lists.newArrayList();
            } else {
                dataRuleList = dataRuleMapper.findByUserId(user);
            }
            UserSessionUtil.putCache(CACHE_DATA_RULE_LIST, dataRuleList);
        }
        return dataRuleList;
    }

    /**
     * 获取当前用户授权菜单
     *
     * @return
     */
    public static Menu getTopMenu() {
        if (getMenuList().size() == 0) {
            return new Menu();
        } else {
            return getMenuList().get(0);
        }
    }

    /**
     * 获取当前用户授权菜单
     *
     * @return
     */
    public static Menu getMainTopMenu(String menuId) {
        return getMenuList().stream().filter(a -> a.getId().equals(menuId)).findAny().orElse(new Menu());
    }


    /**
     * 获取当前用户授权的区域
     *
     * @return
     */
    public static List<Area> getAreaList() {
        return areaService.findList(new Area());
    }

    /**
     * 根据code从缓存获取区域
     *
     * @return
     */
    public static Area getAreaByCode(String code) {
        List<Area> areaList = getAreaList();
        Area area = areaList.stream().filter(a -> a.getCode().equals(code)).findAny().orElse(null);
        return area;
    }

    /**
     * 获取当前用户有权限访问的部门
     *
     * @return
     */
    public static List<Office> getOfficeList() {
        return officeService.findList(new Office());
    }

    /**
     * 获取授权主要对象
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前登录者对象
     */
    public static Principal getPrincipal() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null) {
                return principal;
            }
//			subject.logout();
        } catch (UnavailableSecurityManagerException e) {

        } catch (InvalidSessionException e) {

        }
        return null;
    }

    /**
     * 是否是手机登录
     *
     * @return
     */
    public static boolean isMobileLogin() {
        Principal principal = getPrincipal();
        return principal != null && principal.isMobileLogin();
    }


    //发送注册码
    public static String sendRandomCode(String uid, String pwd, String tel, String randomCode) throws IOException {
        //发送内容
        String content = "您的验证码是：" + randomCode + "，有效期30分钟，请在有效期内使用。";
        return SMSUtils.send(uid, pwd, tel, content);

    }

    //注册用户重置密码
    public static String sendPass(String uid, String pwd, String tel, String password) throws IOException {
        //发送内容
        String content = "您的新密码是：" + password + "，请登录系统，重新设置密码。";
        return SMSUtils.send(uid, pwd, tel, content);

    }

    /**
     * 导出Excel调用,根据姓名转换为ID
     */
    public static User getByUserName(String name) {
        User u = new User();
        u.setName(name);
        List<User> users = userMapper.findList(u);
        return users == null ? new User() : users.stream().filter(a -> a.getName().equals(name)).findAny().orElse(new User());
    }

    /**
     * 导出Excel使用，根据名字转换为id
     */
    public static Office getByOfficeName(String name) {
        return getOfficeList().stream().filter(a -> a.getName().equals(name)).findAny().orElse(null);
    }


    public static boolean hasPermission(String permission) {
        return SecurityUtils.getSubject().isPermitted(permission);
    }

}
