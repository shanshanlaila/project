package com.imooc.project.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.project.dto.LoginDTO;
import com.imooc.project.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.project.entity.Resource;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 账号表 服务类
 * </p>
 *
 * @author shanshan
 * @since 2022-08-03
 */
public interface AccountService extends IService<Account> {


    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    LoginDTO login(String username,String password);


    /**
     * 分页查询账号
     *
     * @param page
     * @param wrapper
     * @return
     */
    IPage<Account> accountPage(Page<Account> page, QueryWrapper<Account> wrapper);

    /**
     * 根据账号id查询账号信息
     * @param id
     * @return
     */
    Account getAccountById(Long id);

}
