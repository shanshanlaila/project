package com.imooc.project.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.swing.border.CompoundBorder;
import java.util.Map;
import java.util.Set;

public class QueryUtil {

    public static <T> MyQuery<T> buildMyQuery(Map<String, String> param) {
        MyQuery<T> myQuery = new MyQuery<>();

        long page = 1L;
        long limit = 10L;

        QueryWrapper<T> wrapper = new QueryWrapper<>();
        //获取键值对的set集合
        Set<Map.Entry<String, String>> entries = param.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String value = entry.getValue();
            if (StringUtils.isNotBlank(value)) {
                String key = entry.getKey();
                if ("page".equals(key)) {
                    page = Long.parseLong(value);
                } else if ("limit".equals(key)) {
                    limit = Long.parseLong(value);
                } else {
                    String[] keyArray = key.split("\\$");//前端自定义传来查询参数
                    switch (keyArray[0]) {
                        case "like":
                            wrapper.like(keyArray[1], value);
                            break;
                        case "ge":
                            wrapper.ge(keyArray[1], value);
                            break;
                        case "le":
                            wrapper.le(keyArray[1], value);
                            break;
                        case "eq":
                        default:
                            wrapper.eq(keyArray[1], value);
                            break;
                    }
                }
            }
        }
        myQuery.setPage(new Page<>(page, limit));
        myQuery.setWrapper(wrapper);
        return myQuery;
    }
}
