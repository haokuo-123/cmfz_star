package com.baizhi.service;

import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDAO chapterDAO;
    @Override
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
    public void update(Chapter chapter) {
        int i = chapterDAO.updateByPrimaryKeySelective(chapter);
        if(i == 0){
            throw  new RuntimeException("修改失败");
        }

    }

}
