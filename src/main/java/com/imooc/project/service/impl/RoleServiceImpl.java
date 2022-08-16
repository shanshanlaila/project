package com.imooc.project.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.imooc.project.entity.Role;
import com.imooc.project.entity.RoleResource;
import com.imooc.project.mapper.ResourceMapper;
import com.imooc.project.mapper.RoleMapper;
import com.imooc.project.mapper.RoleResourceMapper;
import com.imooc.project.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author shanshan
 * @since 2022-08-03
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean savaRole(Role role) {
        save(role);
        Long roleId = role.getRoleId();
        //得到选中的资源
        List<Long> resourceIds = role.getResourceIds();
        System.out.println(resourceIds);
        //遍历资源
        if (CollectionUtils.isNotEmpty(resourceIds)) {
            for (Long resourceId : resourceIds) {
                //向 角色资源中间表 中加入数据
                RoleResource roleResource = new RoleResource();
                roleResource.setRoleId(roleId);
                roleResource.setResourceId(resourceId);
                roleResourceMapper.insert(roleResource);
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRole(Role role) {
        updateById(role);
        Long roleId = role.getRoleId();
        //删除原先的资源
        roleResourceMapper.delete(Wrappers.<RoleResource>lambdaQuery().eq(RoleResource::getRoleId, roleId));
        //得到选中的资源
        List<Long> resourceIds = role.getResourceIds();
        System.out.println(resourceIds);
        //遍历资源
        if (CollectionUtils.isNotEmpty(resourceIds)) {
            for (Long resourceId : resourceIds) {
                //向 角色资源中间表 中加入数据
                RoleResource roleResource = new RoleResource();
                roleResource.setRoleId(roleId);
                roleResource.setResourceId(resourceId);
                roleResourceMapper.insert(roleResource);
            }
        }
        return true;
    }
}
