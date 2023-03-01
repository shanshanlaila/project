/*
 * @Title: project
 * @Description: TODO
 * @author MI
 * @date 2022/8/25 16:30
 * @version
 */
package com.imooc.project.mp;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.AlwaysUpdateSomeColumnById;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.baomidou.mybatisplus.extension.injector.methods.LogicDeleteByIdWithFill;
import com.imooc.project.method.DeleteAllMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author MI
 * @ClassName: MysqlInjector
 * @Description: TODO
 * @date 2022/8/25 16:30
 */

public class MysqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        //选装件：逻辑删除并带填充
        methodList.add(new LogicDeleteByIdWithFill());
        //自定义sql：删除全部
        methodList.add(new DeleteAllMethod());
        //选装件：批量增加(排除逻辑删除和age字段)
        methodList.add(new InsertBatchSomeColumn(t -> !t.isLogicDelete() && !t.getColumn().equals("age")));
        //选装件：更新特定字段(排除name字段)
        methodList.add(new AlwaysUpdateSomeColumnById(t -> !t.getProperty().equals("name")));
        return methodList;
    }

}
