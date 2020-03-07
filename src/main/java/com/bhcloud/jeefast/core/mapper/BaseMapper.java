package com.bhcloud.jeefast.core.mapper;

import java.util.List;

/**
 * Mapper基础实现
 * @param <T>
 */
public interface BaseMapper<T> {

    /**
     * 根据实体获取单条数据
     * @param entity
     * @return
     */
    public T get(T entity);

    /**
     * 根据id获取单条数据
     * @param id
     * @return
     */
    public T get(String id);

    /**
     * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
     * @param entity
     * @return
     */
    public List<T> findList(T entity);

    /**
     * 查询所有数据列表
     * @param entity
     * @return
     */
    public List<T> findAllList(T entity);

    /**
     * 查询所有数据列表
     * @see public List<T> findAllList(T entity)
     * @return
     */
    @Deprecated
    public List<T> findAllList();

    /**
     * 插入数据
     * @param entity
     * @return
     */
    public int insert(T entity);

    /**
     * 更新数据
     * @param entity
     * @return
     */
    public int update(T entity);

    /**
     * 删除数据（物理删除，从数据库中彻底删除）
     * @param id
     * @see public int delete(T entity)
     * @return
     */
    @Deprecated
    public int delete(String id);

    /**
     * 删除数据（物理删除，从数据库中彻底删除）
     * @param entity
     * @return
     */
    public int delete(T entity);

}
