/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeefw.org/">JeeFw</a> All rights reserved.
 */
package com.bhcloud.jeefast.moudles.system.utils;

import com.alibaba.fastjson.JSONObject;
import com.jeefw.common.utils.SpringContextHolder;
import com.jeefw.modules.sys.entity.SysConf;
import com.jeefw.modules.sys.service.SysConfService;

/**
 * 系统配置工具类
 * @author jeefw
 * @version 2016-5-29
 */
public class SysConfUtils {

	private static SysConfService sysConfService = SpringContextHolder.getBean(SysConfService.class);

	public static SysConf getByKey(String key) {
		return sysConfService.getByKey(key);
	}

	public static String getValue(String key, String name) {
		return getValue(key, name, null);
	}

	public static String getValue(String key, String name, String defaultVal) {
		SysConf conf = getByKey(key);
		if (conf == null) {
			return defaultVal;
		}
		JSONObject object = JSONObject.parseObject(conf.getValue());
		String value = object.getString(name);
		return value == null ? defaultVal : value;
	}

}
