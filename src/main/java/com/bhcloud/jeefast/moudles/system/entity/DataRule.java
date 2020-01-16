package com.bhcloud.jeefast.moudles.system.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.bhcloud.jeefast.common.entity.DataEntity;
import com.bhcloud.jeefast.common.utils.StringUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 数据权限Entity
 * @author bh
 * @version 2020-01-16
 */
@Data
@NoArgsConstructor
@ExcelTarget("DataRule")
public class DataRule extends DataEntity<DataRule> {
	
	private static final long serialVersionUID = 1L;
	@Excel(name="所属菜单")
	private String menuId;		// 所属菜单

	@Excel(name ="数据规则名称")
	private String name;		// 数据规则名称

	@Excel(name = "实体类名")
	private String className;   //实体类名

	@Excel(name = "规则字段")
	private String field;		// 规则字段

	@Excel(name = "规则条件")
	private String express;		// 规则条件

	@Excel(name="规则值")
	private String value;		// 规则值

	@Excel(name = "自定义sql")
	private String sqlSegment;		// 自定义sql

	
	public String getDataScopeSql(){
		StringBuffer sqlBuffer = new StringBuffer();
		if(StringUtils.isNotBlank(field)&&StringUtils.isNotBlank(value)){
			sqlBuffer.append(" AND " +field+" "+ StringEscapeUtils.unescapeHtml4(express)+" "+value+" ");
		}
		if(StringUtils.isNotBlank(sqlSegment)){
			sqlBuffer.append(" AND "+ StringEscapeUtils.unescapeHtml4(sqlSegment)+" ");
		}
		
		return sqlBuffer.toString();
	}
	
}