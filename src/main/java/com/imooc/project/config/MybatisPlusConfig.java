/*
 * @Title: project
 * @Description: TODO
 * @author MI
 * @date 2022/8/3 12:50
 * @version
 */
package com.imooc.project.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.imooc.project.mp.MysqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MI
 * @ClassName: MybatisPlusConfig
 * @Description: TODO
 * @date 2022/8/3 12:50
 */
@Configuration
public class MybatisPlusConfig {

    /*

    mybais-plus分页插件的拦截器*/
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return mybatisPlusInterceptor;
    }

    /**
     * 自定义sql注入器
     * @return
     */
    @Bean
    public DefaultSqlInjector sqlInjector(){
        return new MysqlInjector();
    }

}
