package com.yhj.blog.service.Impl;

import com.yhj.blog.dao.mapper.CategoryMapper;
import com.yhj.blog.dao.pojo.Category;
import com.yhj.blog.service.CategoryServie;
import com.yhj.blog.vo.CategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: pc
 * @Date: 2022/1/14
 * @Description:
 **/
@Service
public class CategoryServiceImpl implements CategoryServie {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CategoryVo findCategoryById(Long id){
        Category category = categoryMapper.selectById(id);
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        return categoryVo;
    }
}

