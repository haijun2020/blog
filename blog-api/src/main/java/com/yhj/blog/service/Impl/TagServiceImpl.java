package com.yhj.blog.service.Impl;

import com.yhj.blog.dao.mapper.TagMapper;
import com.yhj.blog.dao.pojo.Tag;
import com.yhj.blog.service.TagService;
import com.yhj.blog.vo.Result;
import com.yhj.blog.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: pc
 * @Date: 2022/1/2
 * @Description:
 **/
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;



    @Override
    public List<TagVo> findTagsByArticleId(Long id) {
        List<Tag> tags = tagMapper.findTagsByArticleId(id);
        ArrayList<TagVo> tagVos = new ArrayList<>();
        TagVo tagVo = new TagVo();

        //将对象进行转换成vo对象
        for (Tag tag : tags) {
            BeanUtils.copyProperties(tag,tagVo);
            tagVos.add(tagVo);
        }

        return tagVos;
    }


    @Override
    public Result hots(int limit) {
        //标签所拥有的文章数量最多  就是 最热标签
        //查询 根据tag_id 分组 计数，从大到小 排列 取前limit个
        List<Long> tagIds = tagMapper.findHotsTagIds(limit);
        if(CollectionUtils.isEmpty(tagIds)){
            return Result.success(Collections.emptyList());
        }

        List<Tag> tagList = tagMapper.findTagsByTagIds(tagIds);


        return Result.success(tagList);
    }
}

