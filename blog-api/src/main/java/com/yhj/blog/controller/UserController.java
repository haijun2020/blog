package com.yhj.blog.controller;

import com.yhj.blog.service.SysUserService;
import com.yhj.blog.vo.Result;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: pc
 * @Date: 2022/1/6
 * @Description:
 **/
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private SysUserService service;

    @GetMapping("currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token){
        return service.findUserByToken(token);
    }
}
