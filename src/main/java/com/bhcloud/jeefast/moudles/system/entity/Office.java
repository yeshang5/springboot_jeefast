package com.bhcloud.jeefast.moudles.system.entity;

import com.bhcloud.jeefast.common.entity.TreeEntity;
import lombok.Data;
import java.util.List;

/**
 * 机构Entity
 * @author bh
 * @version 2020-01-16
 */
@Data
public class Office extends TreeEntity<Office> {

	private static final long serialVersionUID = 1L;
	private Area area;		// 归属区域
	private String code; 	// 机构编码
	private String type; 	// 机构类型（1：公司；2：部门；3：小组）
	private String grade; 	// 机构等级（1：一级；2：二级；3：三级；4：四级）
	private String address; // 联系地址
	private String zipCode; // 邮政编码
	private String master; 	// 负责人
	private String phone; 	// 电话
	private String fax; 	// 传真
	private String email; 	// 邮箱
	private String useable;//是否可用
	private User primaryPerson;//主负责人
	private User deputyPerson;//副负责人
	private List<String> childDeptList;//快速添加子部门
	
	public Office(){
		super();
		this.type = "2";
	}

	@Override
	public Office getParent() {
		return parent;
	}

	@Override
	public void setParent(Office parent) {
		this.parent=parent;
	}
}