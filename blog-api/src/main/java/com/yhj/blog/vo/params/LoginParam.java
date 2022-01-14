package com.yhj.blog.vo.params;

import lombok.Data;

/**
 * @Author: pc
 * @Date: 2022/1/4
 * @Description:
 **/
@Data
public class LoginParam {
    private String account;
    private String password;

    private String nickname;
}
