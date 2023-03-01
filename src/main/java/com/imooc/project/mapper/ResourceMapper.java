package com.imooc.project.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.imooc.project.entity.Resource;
import com.imooc.project.vo.ResourceVO;
import com.imooc.project.vo.TreeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author shanshan
 * @since 2022-08-03
 */
@Mapper
public interface ResourceMapper extends MyMapper<Resource> {

    /**
     * 查询当前登录人的资源
     *
     * @param wrapper
     * @return
     */
    List<ResourceVO> listResource(@Param(Constants.WRAPPER) QueryWrapper<Resource> wrapper);

    List<TreeVO> listResourceById(@Param(Constants.WRAPPER) QueryWrapper<Resource> wrapper, @Param("roleId") Long roleId);
}
