package com.yhj.blog.dao.pojo;

import lombok.Data;

/**
 * @Author: pc
 * @Date: 2022/1/2
 * @Description:
 **/
@Data
public class SysUser {

    private Long id;

    private String account;

    private Integer admin;

    private String avatar;

    private Long createDate;

    private Integer deleted;

    private String email;

    private Long lastLogin;

    private String mobilePhoneNumber;

    private String nickname;

    private String password;

    private String salt;

    private String status;
}
