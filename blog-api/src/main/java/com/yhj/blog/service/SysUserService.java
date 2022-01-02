package com.yhj.blog.service;

import com.yhj.blog.dao.pojo.SysUser;

public interface SysUserService {

    SysUser findUserById(Long id);
}
