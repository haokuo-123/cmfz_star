package com.baizhi.service;

import com.baizhi.annotation.ClearRedisCache;
import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Chapter;
import com.baizhi.annotation.RedisCache;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDAO chapterDAO;
    @Override
    @RedisCache
    public Map<String, Object> findAll(Integer page, Integer rows, String albumId) {
        Chapter chapter = new Chapter();
        chapter.setAlbumId(albumId);
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        List<Chapter> chapters = chapterDAO.selectByRowBounds(chapter, rowBounds);
        int count = chapterDAO.selectCount(chapter);
        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",chapters);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);
        return map;
    }

    @Override
    @ClearRedisCache
    public String save(Chapter chapter) {
        chapter.setId(UUID.randomUUID().toString());
        chapter.setCreateDate(new Date());
        int i = chapterDAO.insertSelective(chapter);
        if(i==0){
            throw new RuntimeException("添加失败");
        }
        return chapter.getId();
    }

    @Override
    @ClearRedisCache
    public void update(Chapter chapter) {
        int i = chapterDAO.updateByPrimaryKeySelective(chapter);
        if(i == 0){
            throw  new RuntimeException("修改失败");
        }

    }

}
