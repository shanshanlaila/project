/*
 * @Title: project
 * @Description: TODO
 * @author MI
 * @date 2022/8/3 19:42
 * @version
 */
package com.imooc.project.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author MI
 * @ClassName: BaseEntity
 * @Description: 基类
 * @date 2022/8/3 19:42
 */
@Data
public class BaseEntity {
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime modifiedTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createAccountId;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long modifiedAccountId;
}
