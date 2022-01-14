package com.yhj.blog.service.Impl;

import com.alibaba.fastjson.JSON;
import com.yhj.blog.dao.pojo.SysUser;
import com.yhj.blog.service.LoginService;
import com.yhj.blog.service.SysUserService;
import com.yhj.blog.utils.JWTUtils;
import com.yhj.blog.vo.ErrorCode;
import com.yhj.blog.vo.Result;
import com.yhj.blog.vo.params.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: pc
 * @Date: 2022/1/4
 * @Description:
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserService service;

    private static  final String slat = "mszlu!@#";

    @Autowired
    private  RedisTemplate<String,String> redisTemplate;



    @Override
    public Result login(LoginParam loginParam) {
        /**
         * 1.检查参数是否合法
         * 2.根据用户名和密码去user表中查询 是否存在
         * 3. 如果不存在 或登录失败
         * 4. 如果存在，使用jwt生成token 返回给前端
         * 5. token放入redis中。 redis token:user信息 设置过期时间
         * （登录认证时 先认证token是否合法，去redis认证是否存在）
         */
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        if(StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            return Result.Result_fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        password = DigestUtils.md5Hex(password+slat);
        SysUser sysUser = service.findUser(account,password);
        if(sysUser == null){
            return Result.Result_fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        String token = JWTUtils.createToken(sysUser.getId());

        //放入redis中，并且设置过期时间为一天
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);


        return Result.success(token);
    }

    /**
     * 效验token是否合法
     * @param token
     * @return
     */
    @Override
    public SysUser checkToken(String token) {
        //效验token
        if(StringUtils.isBlank(token)){
            return null;
        }
        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);
        if(stringObjectMap == null){
            return  null;
        }
        //如果成功
        String s = redisTemplate.opsForValue().get("TOKEN_" + token);
        if(StringUtils.isBlank(s)){
            return null;
        }
        SysUser user = JSON.parseObject(s, SysUser.class);

        return user;
    }

    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_"+token);
        return Result.success(null);
    }

    @Override
    public Result register(LoginParam loginParam) {
        /**
         * 1. 判断参数是否合法
         * 2. 判断用户是否存在， 存在 返回账户已经被注册
         * 3. 不存在，注册用户
         * 4. 生成token
         * 5. 存入redis，并返回
         * 6. 注意加上事务
         */
        String account = loginParam.getAccount();
        String nickname = loginParam.getNickname();
        String password = loginParam.getPassword();
        if(StringUtils.isBlank(account) ||
            StringUtils.isBlank(nickname) ||
                StringUtils.isBlank(password)
        ){
            return Result.Result_fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        SysUser user = service.findUserByAccount(account);
        if(user != null){
            return Result.Result_fail(ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_EXIST.getMsg());
        }
        user = new SysUser();


        user.setNickname(nickname);
        user.setAccount(account);
        user.setPassword(DigestUtils.md5Hex(password+slat));
        user.setCreateDate(System.currentTimeMillis());
        user.setLastLogin(System.currentTimeMillis());
        user.setAvatar("/static/img/logo.b3a48c0.png");
        user.setAdmin(1); //1 为true
        user.setDeleted(0); // 0 为false
        user.setSalt("");
        user.setStatus("");
        user.setEmail("");
        this.service.save(user);

        String token = JWTUtils.createToken(user.getId());

        //放入redis中，并且设置过期时间为一天
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(user),1, TimeUnit.DAYS);



        return Result.success(token);
    }


}
