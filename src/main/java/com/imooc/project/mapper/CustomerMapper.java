package com.imooc.project.mapper;

import com.imooc.project.entity.Customer;
import com.imooc.project.mp.MyMapper;

/**
 * <p>
 * 客户表 Mapper 接口
 * </p>
 *
 * @author shanshan
 * @since 2022-08-03
 */
public interface CustomerMapper extends MyMapper<Customer> {

    @Override
    int deleteByIdWithFill(Customer entity);
}
