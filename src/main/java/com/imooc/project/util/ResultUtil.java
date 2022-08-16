/*
 * @Title: project
 * @Description: TODO
 * @author MI
 * @date 2022/8/5 19:17
 * @version
 */
package com.imooc.project.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MI
 * @ClassName: ResultUtil
 * @Description: TODO
 * @date 2022/8/5 19:17
 */
public class ResultUtil {


    /**
     * 构建分页查询的返回结果
     *
     * @param myPage
     * @return
     */
    public static R<Map<String, Object>> buildPageR(IPage<?> myPage) {
        Map<String, Object> data = new HashMap<>();
        data.put("count", myPage.getTotal());
        data.put("records", myPage.getRecords());
        return R.ok(data);
    }


    /**
     * 处理业务请求结果的方法
     * @param success
     * @return
     */
    public static R<Object> buildR(boolean success) {
        if (success) {
            return R.ok(null);
        }
        return R.failed("新增失败");
    }
}
