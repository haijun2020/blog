package com.yhj.blog.service;

import com.yhj.blog.vo.Result;
import org.springframework.stereotype.Service;

public interface CommentsService {
  Result commentsByArticleId(Long id);
}
