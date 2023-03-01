/*
 * @Title: project
 * @Description: TODO
 * @author MI
 * @date 2022/8/6 17:10
 * @version
 */
package com.imooc.project.mp;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.imooc.project.entity.Account;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author MI
 * @ClassName: MyMetaObjectHandler
 * @Description: 自动填充类
 * @date 2022/8/6 17:10
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入时填充
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Object val = getFieldValByName("createTime", metaObject);
        //判断用户是否自己设置的了值，自己设置了就不自动填充，没有就自动填充
        if (val == null) {
            //自动填充 创建时间
            if (metaObject.hasSetter("createTime")) {
                this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
            }
        }

        //自动填充 创建人
        if (metaObject.hasSetter("createAccountId")) {
            Object account = Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).getAttribute("account", RequestAttributes.SCOPE_SESSION);
            if (account != null) {
                Long accountId = ((Account) account).getAccountId();
                this.strictInsertFill(metaObject, "createAccountId", Long.class, accountId);
            }
        }
    }

    /**
     * 修改时填充
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {

        //自动填充 修改时间
        //判断当前实体类是否具有modifiedTime的set方法
        if (metaObject.hasSetter("modifiedTime")) {
            this.strictUpdateFill(metaObject, "modifiedTime", LocalDateTime.class, LocalDateTime.now());
        }
        System.out.println("自动填充 修改时间");
        //自动填充 修改人
        if (metaObject.hasSetter("modifiedAccountId")) {
            Object account = Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).getAttribute("account", RequestAttributes.SCOPE_SESSION);
            if (account != null) {
                Long accountId = ((Account) account).getAccountId();
                this.strictUpdateFill(metaObject, "modifiedAccountId", Long.class, accountId);
            }
        }
        System.out.println("modifiedAccountId");
    }
}
