package com.yhj.blog.vo.params;

import lombok.Data;

/**
 * @Author: pc
 * @Date: 2022/1/2
 * @Description:
 **/
@Data
public class PageParam {

    private int page = 1;
    private int pageSize = 10;
}
