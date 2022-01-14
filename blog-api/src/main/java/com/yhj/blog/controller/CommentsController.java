package com.yhj.blog.controller;

import com.yhj.blog.service.CommentsService;
import com.yhj.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: pc
 * @Date: 2022/1/14
 * @Description:  /comments/article/{id}
 **/
@RestController
@RequestMapping("comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @GetMapping("/article/{id}")
    public Result listComments(@PathVariable("id") Long id){
        return commentsService.commentsByArticleId(id);
    }
}
