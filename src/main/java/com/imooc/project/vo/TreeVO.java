/*
 * @Title: project
 * @Description: TODO
 * @author MI
 * @date 2022/8/8 12:18
 * @version
 */
package com.imooc.project.vo;

import lombok.Data;

import java.util.List;

/**
 * @author MI
 * @ClassName: TreeVO
 * @Description: 返回给前端的数据格式对象
 * @date 2022/8/8 12:18
 */
@Data
public class TreeVO {

    private String title;

    private Long id;

    private List<TreeVO> children;

    private boolean checked;

}
