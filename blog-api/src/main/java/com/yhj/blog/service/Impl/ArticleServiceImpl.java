package com.yhj.blog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhj.blog.dao.dos.Archives;
import com.yhj.blog.dao.mapper.ArticleBodyMapper;
import com.yhj.blog.dao.mapper.ArticleMapper;
import com.yhj.blog.dao.pojo.Article;
import com.yhj.blog.dao.pojo.ArticleBody;
import com.yhj.blog.service.*;
import com.yhj.blog.vo.ArticleBodyVo;
import com.yhj.blog.vo.ArticleVo;
import com.yhj.blog.vo.params.PageParam;
import com.yhj.blog.vo.Result;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
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
        List<ArticleVo> articleVoList = copyList(records,true,true);

        return Result.success(articleVoList);
    }

    @Autowired
    private ThreadService threadService;

    @Override
    public Result findArticleById(Long articleId) {
        Article article = this.articleMapper.selectById(articleId);


        ArticleVo articleVo = copy(article, true, true,true,true);


        threadService.updateCount(articleMapper,article);

        return Result.success(articleVo);

    }


    //将对象一一转换，并且进行添加到对象集合中。
    private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record,isTag,isAuthor,false,false));
        }
        return articleVoList;
    }

    private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor,boolean isBody) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record,isTag,isAuthor,isBody,false));
        }
        return articleVoList;
    }
    private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor,boolean isBody,boolean isCategory) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record,isTag,isAuthor,isBody,isCategory));
        }
        return articleVoList;
    }

    @Autowired
    private CategoryServie categoryServie;
    //将article对象转换ArticleVo对象
    private ArticleVo copy(Article article,Boolean isTag,Boolean isAuthor,Boolean isBody,Boolean isCategory){

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
        if(isBody){
            Long bodyId = article.getBodyId();
            articleVo.setBody(findArticleBodyById(bodyId));
        }if(isCategory){
            Long categoryId = article.getCategoryId();
            articleVo.setCategory(categoryServie.findCategoryById(categoryId));

        }

        return articleVo;
    }

    @Autowired
    private ArticleBodyMapper articleBodyMapper;

    private ArticleBodyVo findArticleBodyById(Long bodyId) {
        ArticleBody articleBody = articleBodyMapper.selectById(bodyId);
        System.out.println(articleBody);
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        articleBodyVo.setContent(articleBody.getContent());

        return articleBodyVo;
    }


    @Override
    public Result selectHotsArticle(int limit) {
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //通过该参数进行排序，降序
        articleLambdaQueryWrapper.orderByDesc(Article::getViewCounts);
        //查询
        articleLambdaQueryWrapper.select(Article::getId,Article::getTitle);
        //最后拼接的sql语言是：select id,title from article order by view_counts desc limit 5;
        articleLambdaQueryWrapper.last("limit "+limit);

        List<Article> articleList = articleMapper.selectList(articleLambdaQueryWrapper);

        return Result.success(copyList(articleList,true,true,false,false));


    }


    @Override
    public Result selectNewsArticle(int limit) {
        LambdaQueryWrapper<Article> QueryWrapper = new LambdaQueryWrapper<>();
        //对时间进行排序，降序
        QueryWrapper.orderByDesc(Article::getCreateDate);

        QueryWrapper.select(Article::getId,Article::getTitle);

        //拼接sql语句加上limit xxx
        QueryWrapper.last("limit "+limit);

        //进行查询
        List<Article> articleList = articleMapper.selectList(QueryWrapper);

        return Result.success(copyList(articleList,true,true,false,false));
    }

    /**
     * 文章归档
     * @return
     */
    @Override
    public Result listArchives() {
       List<Archives> archives =  articleMapper.listArchives();

        return Result.success(archives);

    }


}
