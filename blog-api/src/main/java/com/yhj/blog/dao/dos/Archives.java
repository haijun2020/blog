package com.yhj.blog.dao.dos;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author: pc
 * @Date: 2022/1/4
 * @Description:
 **/
@Data
public class Archives {
    private Integer year;

    private Integer  month;

    private Long count;
}
