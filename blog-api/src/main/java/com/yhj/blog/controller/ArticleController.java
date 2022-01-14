package com.yhj.blog.controller;

import com.yhj.blog.service.ArticleService;
import com.yhj.blog.vo.params.PageParam;
import com.yhj.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 最热文章
     * @return
     */
    @PostMapping("hot")
    public Result hotsArticle(){
        int limit = 5;
        return articleService.selectHotsArticle(limit);
    }

    /**
     * 最新文章
     * @return
     */
    @PostMapping("new")
    public Result newArticle(){
        int limit = 3;
        return articleService.selectNewsArticle(limit);
    }

//    /articles/listArchives
    @PostMapping("listArchives")
    public Result listArchives(){
        return articleService.listArchives();
    }

    @PostMapping("view/{id}")
    public Result findArticleById(@PathVariable("id") Long articleId){
        return articleService.findArticleById(articleId);
    }

}
