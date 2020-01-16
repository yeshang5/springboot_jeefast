package com.bhcloud.jeefast.moudles.system.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.bhcloud.jeefast.common.entity.DataEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据字典Entity
 * @author lgf
 * @version 2017-01-16
 */
@Data
@NoArgsConstructor
public class DictValue extends DataEntity<DictValue> {
	
	private static final long serialVersionUID = 1L;

	@Excel(name = "标签名")
	private String label;		// 标签名

	@Excel(name = "键值")
	private String value;		// 键值

	@Excel(name = "排序")
	private String sort;		// 排序

	private DictType dictType;		// 外键 父类
	

	
}