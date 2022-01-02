package com.yhj.blog.controller;

import com.yhj.blog.service.TagService;
import com.yhj.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: pc
 * @Date: 2022/1/2
 * @Description:
 **/
@RestController
@RequestMapping("/tags")
public class TagsController {

    @Autowired
    private TagService tagService;

    @GetMapping("hot")
    public Result hot(){
        int limit = 6;
         return tagService.hots(limit);
    }
}
