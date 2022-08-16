/*
 * @Title: project
 * @Description: TODO
 * @author MI
 * @date 2022/8/7 12:19
 * @version
 */
package com.imooc.project.query;

import lombok.Data;

/**
 * @author MI
 * @ClassName: AccountQuery
 * @Description: 账号查询条件
 * @date 2022/8/7 12:19
 */

@Data
public class AccountQuery {

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间范围
     */
    private String createTimeRange;

    /**
     * 当前页
     */
    private Long page;

    /**
     * 每页条数
     */
    private Long limit;
}
