package com.bhcloud.jeefast.core.service;

import cn.hutool.core.util.StrUtil;
import com.bhcloud.jeefast.core.entity.BaseEntity;
import com.bhcloud.jeefast.core.mapper.BaseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
public abstract class BaseService<M extends BaseMapper<T>, T extends BaseEntity> {

    @Autowired
    private M mapper;

    /**
     * 查询所有
     * @param entity
     * @return
     */
    public List<T> findList(T entity){
        return mapper.findList(entity);
    };

    /**
     * 分页查询，默认分页，当参数pageSize=0时不分页
     * @param entity
     * @param request
     * @return
     */
    public PageInfo<T> findPage(T entity, HttpServletRequest request, HttpServletResponse response){
        String pageNumStr= request.getParameter("pageNum");
        String pageSizeStr= request.getParameter("pageSize");
        Integer pageNum= StrUtil.isNotBlank(pageNumStr)?Integer.valueOf(pageNumStr):1;
        Integer pageSize=StrUtil.isNotBlank(pageSizeStr)?Integer.valueOf(pageSizeStr):10;
        PageHelper.startPage(pageNum,pageSize);
        List<T> list=mapper.findList(entity);
        PageInfo<T> pageInfo=new PageInfo<T>(list);
        return pageInfo;
    }
}
