package com.yhj.blog.handler;

import com.yhj.blog.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: pc
 * @Date: 2022/1/3
 * @Description: 对异常进行统一的管理
 **/
//对添加了Controller的方法进行拦截处理AOP处理，进行切面增强
@ControllerAdvice
public class AllExceptionHandler {

    //进行异常处理，处理Exception.class的异常
    //使用ResponseBody返回json数据
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result doException(Exception e){
        e.printStackTrace();
        return Result.Result_fail(-999,"系统异常");
    }

}
