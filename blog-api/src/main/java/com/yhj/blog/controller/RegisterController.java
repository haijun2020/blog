package com.yhj.blog.controller;

import com.yhj.blog.service.LoginService;
import com.yhj.blog.vo.Result;
import com.yhj.blog.vo.params.LoginParam;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: pc
 * @Date: 2022/1/7
 * @Description:
 **/
@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result register(@RequestBody LoginParam loginParam){

        return loginService.register(loginParam);


    }
}
