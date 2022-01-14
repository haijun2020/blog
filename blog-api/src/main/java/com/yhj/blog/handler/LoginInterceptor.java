package com.yhj.blog.handler;

import com.alibaba.fastjson.JSON;
import com.yhj.blog.dao.pojo.SysUser;
import com.yhj.blog.service.LoginService;
import com.yhj.blog.service.SysUserService;
import com.yhj.blog.utils.UserThreadLocal;
import com.yhj.blog.vo.ErrorCode;
import com.yhj.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: pc
 * @Date: 2A022/1/13
 **/
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在执行controller方法(Handler)之前执行
        /*
        1. 需要判断请求的接口路径是否为：handlerMethod(controller方法)
        2. 判断token是否为空，如果为空未登录
        3. 如果token下为空，登录验证loginService checkToken
        4. 如果认证成功，放行就好
         */
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        //验证是否携带token
        String token = request.getHeader("Authorization");

        log.info(("=====================request start=========================="));
        String requestURI = request.getRequestURI();
        log.info("request uri:{}",requestURI);
        log.info("request method:{}",request.getMethod());
        log.info("token:{}",token);
        log.info("=====================request end==============================");


        if(StringUtils.isBlank(token)){
            //如果为空
            Result result = Result.Result_fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        //验证用户是否存在
        SysUser user = service.checkToken(token);
        if(user == null){
            //如果为空
            Result result = Result.Result_fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        //登录验证成功，放行
        UserThreadLocal.put(user);
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //删除内存中的用户
        UserThreadLocal.remove();

    }
}
