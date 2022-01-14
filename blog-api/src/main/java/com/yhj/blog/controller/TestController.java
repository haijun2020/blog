package com.yhj.blog.controller;

import com.yhj.blog.dao.pojo.SysUser;
import com.yhj.blog.utils.UserThreadLocal;
import com.yhj.blog.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: pc
 * @Date: 2022/1/14
 * @Description:
 **/
@RestController
public class TestController {

    @GetMapping("/test")
    public Result test1(){

        SysUser user = UserThreadLocal.get();
        System.out.println(user);

        return Result.Result_fail(100,null);
    }


}
