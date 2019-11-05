package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.Map;

public interface ArticleService {
    Map<String,Object> findAll(Integer page, Integer rows);
    void save(Article article);
    void update(Article article);
    void delete(String id);
}
