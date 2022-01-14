package com.yhj;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yhj.blog.dao.mapper.SysUserMapper;
import com.yhj.blog.dao.pojo.SysUser;
import com.yhj.blog.utils.JWTUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: pc
 * @Date: 2022/1/4
 * @Description:
 **/

@SpringBootTest
public class test1 {

        @Autowired
        private RedisTemplate<String,String> redisTemplate;
        @Autowired
        private SysUserMapper sysUserMapper;

        @Test
        public void set(){
            redisTemplate.opsForValue().set("myKey","myValue");
            System.out.println(redisTemplate.opsForValue().get("myKey"));
        }



}
