package com.yhj.blog.vo;

import lombok.Data;

/**
 * @Author: pc
 * @Date: 2022/1/6
 * @Description:
 **/
@Data
public class LoginUserVo {
    private Long id;
    private String account;
    private String nickname;
    private String avatar;
}
