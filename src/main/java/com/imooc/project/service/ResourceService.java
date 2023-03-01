package com.imooc.project.service;

import com.imooc.project.entity.Resource;
import com.imooc.project.mp.MyService;
import com.imooc.project.vo.ResourceVO;
import com.imooc.project.vo.TreeVO;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author shanshan
 * @since 2022-08-03
 */
public interface ResourceService extends MyService<Resource> {

    List<ResourceVO> listResourceByRoleId(Long roleId);

    /**
     * 查询系统资源，供前端组建渲染
     *
     * @return
     */
    List<TreeVO> listResource(Long roleId);
}
