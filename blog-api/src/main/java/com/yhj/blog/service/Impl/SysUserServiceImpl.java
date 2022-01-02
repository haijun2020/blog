package com.yhj.blog.service.Impl;

import com.yhj.blog.dao.mapper.SysUserMapper;
import com.yhj.blog.dao.pojo.SysUser;
import com.yhj.blog.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: pc
 * @Date: 2022/1/2
 * @Description:
 **/
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findUserById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if(sysUser == null){
            sysUser = new SysUser();
            sysUser.setNickname("CodeHai");
        }
        return sysUser;
    }
}
