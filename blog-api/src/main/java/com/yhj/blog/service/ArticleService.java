package com.yhj.blog.service;

import com.yhj.blog.vo.params.PageParam;
import com.yhj.blog.vo.Result;

public interface ArticleService {
    Result listArticle(PageParam pageParam);

    Result selectHotsArticle(int limit);

    Result selectNewsArticle(int limit);

    Result listArchives();

    Result findArticleById(Long articleId);
}
