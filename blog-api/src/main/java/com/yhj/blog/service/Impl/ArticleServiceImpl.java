package com.yhj.blog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhj.blog.dao.mapper.ArticleMapper;
import com.yhj.blog.dao.pojo.Article;
import com.yhj.blog.service.ArticleService;
import com.yhj.blog.service.SysUserService;
import com.yhj.blog.service.TagService;
import com.yhj.blog.vo.ArticleVo;
import com.yhj.blog.vo.PageParam;
import com.yhj.blog.vo.Result;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: pc
 * @Date: 2022/1/2
 * @Description:
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private SysUserService service;

    /**
     * 1. 分页查询article数据表
     * @param pageParam
     * @return
     */
    @Override
    public Result listArticle(PageParam pageParam) {

        //通过page进行分页
        Page<Article> articlePage = new Page<>(pageParam.getPage(),pageParam.getPageSize());
        LambdaQueryWrapper<Article> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //通过置顶进行排序
        objectLambdaQueryWrapper.orderByDesc(Article::getWeight);
        //通过产生时间进行降序查询
        objectLambdaQueryWrapper.orderByDesc(Article::getCreateDate);

        Page<Article> articlePage1 = articleMapper.selectPage(articlePage, objectLambdaQueryWrapper);
        List<Article> records = articlePage1.getRecords();
        //返回查询到的数据，但是一般来说查询到的数据不能直接进行返回，而是通过vo对象，返回对应的数据
        List<ArticleVo> articleVoList = copyList(records);

        return Result.success(articleVoList);
    }

    //将对象一一转换，并且进行添加到对象集合中。
    private List<ArticleVo> copyList(List<Article> records) {
        List<ArticleVo> articleList = new ArrayList<>();
        for (Article record : records) {
            articleList.add(copy(record,true,true));
        }
        return articleList;
    }

    //将article对象转换ArticleVo对象
    private ArticleVo copy(Article article,Boolean isTag,Boolean isAuthor){

        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article,articleVo);

        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));

        //并不是所有的接口 都需要标签，作者信息
        if(isTag){
            Long id = article.getId();
            articleVo.setTags(tagService.findTagsByArticleId(id));
        }
        if(isAuthor){
            Long id = article.getId();
            articleVo.setAuthor(service.findUserById(id).getNickname());
        }

        return articleVo;
    }



}
