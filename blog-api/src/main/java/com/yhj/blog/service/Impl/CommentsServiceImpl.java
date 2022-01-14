package com.yhj.blog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yhj.blog.dao.mapper.CommentMapper;
import com.yhj.blog.dao.pojo.Comment;
import com.yhj.blog.service.CommentsService;
import com.yhj.blog.service.LoginService;
import com.yhj.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author: pc
 * @Date: 2022/1/14
 * @Description:
 **/
@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentMapper commentMapper;

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Result commentsByArticleId(Long id) {

        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId,id);
        queryWrapper.eq(Comment::getLevel,1);
        List<Comment> comments = commentMapper.selectList(queryWrapper);



        return null;
    }
}
