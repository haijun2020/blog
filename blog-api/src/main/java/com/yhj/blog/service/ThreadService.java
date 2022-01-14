package com.yhj.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yhj.blog.dao.mapper.ArticleMapper;
import com.yhj.blog.dao.pojo.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author: pc
 * @Date: 2022/1/14
 * @Description:
 **/
@Component
public class ThreadService {

    //期望此操作在线程池中执行，不影响原有的主线程
    @Async("taskExecutor")
    public void updateCount(ArticleMapper articleMapper, Article article) {

        int viewCounts = article.getViewCounts();
        Article article1 = new Article();
        article1.setViewCounts(viewCounts+1);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getId,article.getId());

        queryWrapper.eq(Article::getViewCounts,viewCounts);

        articleMapper.update(article1,queryWrapper);
        try {
            Thread.sleep(5000);
            System.out.println("更新完成了.....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
