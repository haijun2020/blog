package com.yhj.blog.controller;

import com.yhj.blog.service.ArticleService;
import com.yhj.blog.vo.PageParam;
import com.yhj.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: pc
 * @Date: 2022/1/2
 * @Description:
 **/
//json数据进行交互
@RestController
//
@RequestMapping("articles")
public class ArticleController {


    @Autowired
    private ArticleService articleService;
    /**
     * 首页 文章列表
     * @param pageParam
     * @return
     */
    @PostMapping
    public Result listArticle(@RequestBody PageParam pageParam){
        Result result = articleService.listArticle(pageParam);

        return result;
    }


}
