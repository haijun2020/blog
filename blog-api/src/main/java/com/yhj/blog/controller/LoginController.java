package com.yhj.blog.controller;

import com.yhj.blog.service.LoginService;
import com.yhj.blog.vo.Result;
import com.yhj.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: pc
 * @Date: 2022/1/4
 * @Description:
 **/
@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody LoginParam loginParam){
        return loginService.login(loginParam);

    }
}
