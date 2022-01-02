package com.yhj.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: pc
 * @Date: 2022/1/2
 * @Description:
 **/
@Data
@AllArgsConstructor
public class Result {

    //返回成功还是失败
    private  Boolean success;

    //返回代码
    private int code;

    //返回信息
    private String msg;

    //返回数据
    private Object data;

    //使用静态方法进行返回
    public static Result success(Object data){
        return new Result(true,200,"success",data);
    }

    public static  Result Result_fail(int code,String msg){
        return new Result(false,code,msg,null);
    }

}
