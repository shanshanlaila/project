package com.imooc.project.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface MyMapper<T> extends BaseMapper<T> {

    /**
     * 逻辑删除带自动填充功能
     * @param entity
     * @return
     */
    int deleteByIdWithFill(T entity);
}
