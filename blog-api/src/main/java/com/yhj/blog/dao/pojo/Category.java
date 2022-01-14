package com.yhj.blog.dao.pojo;

import lombok.Data;

/**
 * @Author: pc
 * @Date: 2022/1/14
 * @Description:
 **/
@Data
public class Category {
    private long id;

    private String avatar;

    private String categoryName;

    private String description;
}
