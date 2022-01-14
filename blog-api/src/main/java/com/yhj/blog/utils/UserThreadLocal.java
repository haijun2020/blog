package com.yhj.blog.utils;

import com.yhj.blog.dao.pojo.SysUser;

/**
 * @Author: pc
 * @Date: 2022/1/14
 * @Description:
 **/
public class UserThreadLocal {
    private UserThreadLocal(){
    }

    private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

    public static void put(SysUser sysUser){
        LOCAL.set(sysUser);
    }

    public static SysUser get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }

}
