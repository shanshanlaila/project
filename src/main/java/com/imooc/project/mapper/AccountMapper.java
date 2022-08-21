package com.imooc.project.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.project.entity.Account;
import com.imooc.project.mp.MyMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 账号表 Mapper 接口
 * </p>
 *
 * @author shanshan
 * @since 2022-08-03
 */
public interface AccountMapper extends MyMapper<Account> {


    /**
     * 分页查询账号
     *
     * @param page
     * @param wrapper
     * @return
     */
    IPage<Account> accountPage(Page<Account> page, @Param(Constants.WRAPPER) QueryWrapper<Account> wrapper);

    /**
     * 根据账号id查询账号信息
     * @param id
     * @return
     */
    Account selectAccountById(Long id);

}
