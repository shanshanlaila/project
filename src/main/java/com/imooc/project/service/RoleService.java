package com.imooc.project.service;

import com.imooc.project.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author shanshan
 * @since 2022-08-03
 */
public interface RoleService extends MyService<Role> {

    /**
     * 新增角色和角色所具有的资源
     *
     * @param role
     * @return
     */
    boolean savaRole(Role role);

    /**
     * 修改角色及角色所具有的资源
     *
     * @param role
     * @return
     */
    boolean updateRole(Role role);
}
