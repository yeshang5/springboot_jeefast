package com.bhcloud.jeefast.common.mapper;

import java.util.List;

public interface BaseMapper<T> {
    public T get(T entity);
    public void insert(T entity);
    public void update(T entity);
    public void delete(T entity);
    public List<T> findList(T entity);
}
