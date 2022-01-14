package com.yhj.blog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yhj.blog.dao.mapper.SysUserMapper;
import com.yhj.blog.dao.pojo.SysUser;
import com.yhj.blog.service.LoginService;
import com.yhj.blog.service.SysUserService;
import com.yhj.blog.vo.ErrorCode;
import com.yhj.blog.vo.LoginUserVo;
import com.yhj.blog.vo.Result;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private LoginService loginService;

    @Override
    public SysUser findUserById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if(sysUser == null){
            sysUser = new SysUser();
            sysUser.setNickname("CodeHai");
        }
        return sysUser;
    }

    @Override
    public SysUser findUser(String account, String password) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,account);
        queryWrapper.eq(SysUser::getPassword,password);
        queryWrapper.select(SysUser::getAccount,SysUser::getAvatar,SysUser::getNickname);
        queryWrapper.last("limit 1");

        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public Result findUserByToken(String token) {
        /**
         * 1.token合法性效验
         *  是否为空，解析是否成功，redis是否成功
         * 2.如果失败 返回错误
         * 3. 如果成功，返回对应的结果 LoginUserVo
         */
        //解析是否成功，redis是否成功
        SysUser user = loginService.checkToken(token);
        if(user == null){
            return Result.Result_fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
        }
        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setId(user.getId());
        loginUserVo.setNickname(user.getNickname());
        loginUserVo.setAvatar(user.getAvatar());
        loginUserVo.setAccount(user.getAccount());


        return Result.success(loginUserVo);
    }


    @Override
    public SysUser findUserByAccount(String account) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,account);
        queryWrapper.last("limit 1");
        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public void save(SysUser user) {

        sysUserMapper.insert(user);

    }
}
