package com.yhj.blog.service;

import com.yhj.blog.vo.CategoryVo;
import org.springframework.stereotype.Service;

/**
 * @Author: pc
 * @Date: 2022/1/14
 * @Description:
 **/
public interface CategoryServie {
    public CategoryVo findCategoryById(Long categoryId);
}
