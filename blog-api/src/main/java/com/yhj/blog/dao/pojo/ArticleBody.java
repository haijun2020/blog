package com.yhj.blog.dao.pojo;

import lombok.Data;

/**
 * @Author: pc
 * @Date: 2022/1/14
 * @Description:
 **/
@Data
public class ArticleBody {

    private Long id;

    private String content;

    private String contentHtml;

    private Long articleId;
}
