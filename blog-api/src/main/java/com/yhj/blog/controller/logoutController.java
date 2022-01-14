package com.yhj.blog.controller;

import com.yhj.blog.service.LoginService;
import com.yhj.blog.service.SysUserService;
import com.yhj.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: pc
 * @Date: 2022/1/6
 * @Description:
 **/
@RestController
@RequestMapping("logout")
public class logoutController {

    @Autowired
    private LoginService service;

    @GetMapping
    public Result logout(@RequestHeader("Authorization") String token){
        return service.logout(token);
    }
}
