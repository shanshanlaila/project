package com.imooc.project.mapper;

import com.imooc.project.entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 客户表 Mapper 接口
 * </p>
 *
 * @author shanshan
 * @since 2022-08-03
 */
@Mapper
public interface CustomerMapper extends MyMapper<Customer> {
    Customer selectByRealName(@Param("realName") String name);
}
