package com.imooc.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.imooc.project.mp.MyMapper;

public interface MyService<T> extends IService<T> {

    default boolean removeByIdWithFill(T entity){
        int count = ((MyMapper<T>) getBaseMapper()).deleteByIdWithFill(entity);
        return SqlHelper.retBool(count);//大于0返回true，小于0返回false
    }
}
