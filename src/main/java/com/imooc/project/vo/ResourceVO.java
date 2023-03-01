/*
 * @Title: project
 * @Description: TODO
 * @author MI
 * @date 2022/8/4 15:21
 * @version
 */
package com.imooc.project.vo;

import lombok.Data;

import java.util.List;

/**
 * @author MI
 * @ClassName: ResourceVO
 * @Description: 返回给前端侧边栏动态展示的ViewObject类
 * @date 2022/8/4 15:21
 */

@Data
public class ResourceVO {

    /**
     * 主键
     */
    private Long resourceId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 子资源
     */
    private List<ResourceVO> subs;
}
