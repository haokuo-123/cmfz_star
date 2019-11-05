package com.baizhi.service;

import com.baizhi.dao.ArticleDAO;
import com.baizhi.entity.Article;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDAO articleDAO;


    @Override
    public Map<String, Object> findAll(Integer page, Integer rows) {
           Article article = new Article();
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        List<Article> articles = articleDAO.selectByRowBounds(article, rowBounds);
        int count = articleDAO.selectCount(article);
        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",articles);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);
        return map;
    }

    @Override
    public void save(Article article) {
        article.setId(UUID.randomUUID().toString());
        article.setCreateDate(new Date());
        articleDAO.insertSelective(article);
    }

    @Override
    public void update(Article article) {
        articleDAO.updateByPrimaryKeySelective(article);
    }

    @Override
    public void delete(String id) {
        articleDAO.deleteByPrimaryKey(id);
    }
}
