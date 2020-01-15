/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeefw.org/">JeeFw</a> All rights reserved.
 */
package com.bhcloud.jeefast.common.mapper;

import com.bhcloud.jeefast.common.entity.TreeEntity;

import java.util.List;

/**
 * DAO支持类实现
 * @author jeefw
 * @version 2017-05-16
 * @param <T>
 */
public interface TreeMapper<T extends TreeEntity<T>> extends BaseMapper<T> {

	/**
	 * 找到所有子节点
	 * @param entity
	 * @return
	 */
	public List<T> findByParentIdsLike(T entity);

	/**
	 * 更新所有父节点字段
	 * @param entity
	 * @return
	 */
	public int updateParentIds(T entity);
	
	
	public int updateSort(T entity);
	
	public List<T> getChildren(String parentId);
	
	
}