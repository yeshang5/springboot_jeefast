/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeefw.org/">JeeFw</a> All rights reserved.
 */
package com.bhcloud.jeefast.moudles.system.entity;


import com.jeefw.common.utils.excel.annotation.ExcelField;
import com.jeefw.core.persistence.DataEntity;

/**
 * 系统配置Entity
 * @author fuyb
 * @version 2018-08-29
 */
public class SysConf extends DataEntity<SysConf> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 配置项
	private String value;		// 配置值
	
	public SysConf() {
		super();
		this.setIdType(IDTYPE_AUTO);
	}

	public SysConf(String name) {
		this.name =name;
	}

	@ExcelField(title="配置项", align=2, sort=1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title="配置值", align=2, sort=2)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}