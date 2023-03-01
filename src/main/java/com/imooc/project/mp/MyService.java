/*
 * @Title: project
 * @Description: TODO
 * @author MI
 * @date 2022/8/25 16:55
 * @version
 */
package com.imooc.project.mp;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.imooc.project.mapper.MyMapper;

/**
 * @author MI
 * @ClassName: MyService
 * @Description: TODO
 * @date 2022/8/25 16:55
 */
public interface MyService<T> extends IService<T> {

    default boolean removeByIdWithFill(T entity) {
        int count = ((MyMapper<T>) getBaseMapper()).deleteByIdWithFill(entity);
        return SqlHelper.retBool(count);
    }


}
