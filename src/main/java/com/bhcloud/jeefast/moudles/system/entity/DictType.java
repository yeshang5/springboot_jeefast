package com.bhcloud.jeefast.moudles.system.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.bhcloud.jeefast.common.entity.DataEntity;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 数据字典Entity
 * @author bh
 * @version 2020-01-16
 */
@Data
@ExcelTarget("DictType")
@NoArgsConstructor
public class DictType extends DataEntity<DictType> {
	
	private static final long serialVersionUID = 1L;
	@Excel(name = "类型")
	private String type;		// 类型

	@Excel(name = "描述")
	private String description;		// 描述

	@ExcelCollection(name = "字典值")
	private List<DictValue> dictValueList = Lists.newArrayList();		// 子表列表
}