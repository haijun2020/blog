package com.yhj.blog.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: pc
 * @Date: 2022/1/2
 * @Description:
 **/
@Configuration
@MapperScan("com.yhj.blog.dao.mapper")
public class MybatisPlusConfig {

    //添加分页插件
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){

        //创建一个拦截器
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        //拦截器中添加分页插件
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        //将拦截器放入ioc容器中
        return mybatisPlusInterceptor;
    }




}
