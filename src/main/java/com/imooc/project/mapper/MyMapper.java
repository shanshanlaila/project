/*
 * @Title: project
 * @Description: TODO
 * @author MI
 * @date 2022/8/25 16:35
 * @version
 */
package com.imooc.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author MI
 * @ClassName: MyMapper
 * @Description: TODO
 * @date 2022/8/25 16:35
 */
public interface MyMapper<T> extends BaseMapper<T> {

    /**
     * 逻辑删除带自动填充功能
     * @param entity
     * @return
     */
    int deleteByIdWithFill(T entity);

    /**
     * 删除全部
     * @return
     */
    int deleteAll();

    /**
     * 批量增加数据字段
     * @param entityList
     * @return
     */
    int insertBatchSomeColumn(List entityList);

    /**
     * 更新特定字段
     * @param entity
     * @return
     */
    int alwaysUpdateSomeColumnById(@Param(Constants.ENTITY) T entity);

}
