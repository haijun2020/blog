package com.yhj.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhj.blog.dao.dos.Archives;
import com.yhj.blog.dao.pojo.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: pc
 * @Date: 2022/1/2
 * @Description:
 **/
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    List<Archives> listArchives();
}
