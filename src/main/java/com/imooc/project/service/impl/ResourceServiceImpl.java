package com.imooc.project.service.impl;

import cn.hutool.db.sql.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.imooc.project.entity.Resource;
import com.imooc.project.mapper.ResourceMapper;
import com.imooc.project.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.project.vo.ResourceVO;
import com.imooc.project.vo.TreeVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UnknownFormatConversionException;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author shanshan
 * @since 2022-08-03
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Override
    public List<ResourceVO> listResourceByRoleId(Long roleId) {
        //查询一级资源的条件构造器
        QueryWrapper<Resource> query = Wrappers.query();
        query.eq("rr.role_id", roleId).isNull("re.parent_id").orderByAsc("re.sort");
        List<ResourceVO> resourceVOS = baseMapper.listResource(query);

        //遍历一级资源目录里面的二级资源
        resourceVOS.forEach(r -> {
            Long resourceId = r.getResourceId();
            QueryWrapper<Resource> subQuery = Wrappers.query();
            subQuery.eq("rr.role_id", roleId).eq("re.parent_id", resourceId).orderByAsc("re.sort");
            List<ResourceVO> subResourceVOS = baseMapper.listResource(subQuery);
            if (CollectionUtils.isNotEmpty(subResourceVOS)) {
                r.setSubs(subResourceVOS);
            }
        });
        return resourceVOS;
    }

    @Override
    public List<TreeVO> listResource(Long roleId) {
        if (roleId == null) {
            //新增角色所用
            LambdaQueryWrapper<Resource> wrapper = Wrappers.<Resource>lambdaQuery()
                    .isNull(Resource::getParentId)
                    .orderByAsc(Resource::getSort);
            List<Resource> resource = list(wrapper);

            //一级资源
            List<TreeVO> treeVOS = resource.stream().map(r -> {
                TreeVO treeVO = new TreeVO();
                treeVO.setId(r.getResourceId());
                treeVO.setTitle(r.getResourceName());

                LambdaQueryWrapper<Resource> subWrapper = Wrappers.<Resource>lambdaQuery()
                        .eq(Resource::getParentId, r.getResourceId())
                        .orderByAsc(Resource::getSort);
                List<Resource> subResource = list(subWrapper);
                if (CollectionUtils.isNotEmpty(subResource)) {
                    //二级资源
                    List<TreeVO> children = subResource.stream().map(sub -> {
                        TreeVO subTreeVO = new TreeVO();
                        subTreeVO.setId(sub.getResourceId());
                        subTreeVO.setTitle(sub.getResourceName());
                        return subTreeVO;
                    }).collect(Collectors.toList());
                    treeVO.setChildren(children);
                }
                return treeVO;
            }).collect(Collectors.toList());
            return treeVOS;
        } else {
            //修改角色所用
            QueryWrapper<Resource> query = Wrappers.query();
            query.isNull("re.parent_id").orderByAsc("re.sort");
            List<TreeVO> treeVOS = baseMapper.listResourceById(query, roleId);
            treeVOS.forEach(t -> {
                t.setChecked(false);
                Long id = t.getId();
                QueryWrapper<Resource> subQuery = Wrappers.<Resource>query();
                subQuery.eq("re.parent_id", id).orderByAsc("re.sort");

                List<TreeVO> children = baseMapper.listResourceById(subQuery, roleId);
                if (CollectionUtils.isNotEmpty(children)) {
                    t.setChildren(children);
                }
            });
            return treeVOS;
        }
    }
}
