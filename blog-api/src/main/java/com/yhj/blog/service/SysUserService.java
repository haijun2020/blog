package com.yhj.blog.service;

import com.yhj.blog.dao.pojo.SysUser;
import com.yhj.blog.vo.Result;

public interface SysUserService {

    SysUser findUserById(Long id);

    SysUser findUser(String account, String password);

    Result findUserByToken(String token);

    /**
     * 根据账户查找用户
     * @param account
     * @return
     */
    SysUser findUserByAccount(String account);

    /**
     * 保存用户
     * @param user
     */
    void save(SysUser user);
}
