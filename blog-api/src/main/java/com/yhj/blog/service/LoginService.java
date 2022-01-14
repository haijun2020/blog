package com.yhj.blog.service;

import com.yhj.blog.dao.pojo.SysUser;
import com.yhj.blog.vo.Result;
import com.yhj.blog.vo.params.LoginParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: pc
 * @Date: 2022/1/4
 * @Description:
 **/
@Transactional
public interface LoginService {
    public Result login(LoginParam loginParam);

    SysUser checkToken(String token);

    Result logout(String token);

    Result register(LoginParam loginParam);
}
