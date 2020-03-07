package com.bhcloud.jeefast.core.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BaseController {
    public Map getBootstrapData(PageInfo pageInfo)
    {
        Map map=new HashMap<>();
        map.put("total", pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

}
