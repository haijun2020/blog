package com.yhj.blog.service;

import com.yhj.blog.vo.PageParam;
import com.yhj.blog.vo.Result;

public interface ArticleService {
    Result listArticle(PageParam pageParam);
}
