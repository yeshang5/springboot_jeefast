package com.bhcloud.jeefast.moudles.system.entity;

import com.bhcloud.jeefast.common.entity.TreeEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 区域实体类
 * @author bh
 * @version 2020-01-15
 */
@Data
public class Area extends TreeEntity<Area> {

	private static final long serialVersionUID = 1L;

	private String code; 	// 区域编码
	private String type; 	// 区域类型（1：国家；2：省份、直辖市；3：地市；4：区县；5：乡镇）
	
	public Area(){
		super();
		this.sort = 30;
	}

	@Override
	public Area getParent() {
		return parent;
	}

	@Override
	public void setParent(Area parent) {
		this.parent = parent;
	}

	
	@Override
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}

}