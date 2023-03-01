package com.imooc.project.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import com.imooc.project.enums.SexEnum;
import lombok.Data;

/**
 *
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField(condition = SqlCondition.LIKE)//当
    private String name;

    /**
     *
     */
    private SexEnum sex;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
