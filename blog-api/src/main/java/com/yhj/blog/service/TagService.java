package com.yhj.blog.service;

import com.yhj.blog.dao.pojo.Tag;
import com.yhj.blog.vo.Result;
import com.yhj.blog.vo.TagVo;

import java.util.List;

public interface TagService {
    /**
     * 根据文章id查询标签列表
     * @param articleId
     * @return
     */
    List<TagVo> findTagsByArticleId(Long articleId);

    Result hots(int limit);
}
