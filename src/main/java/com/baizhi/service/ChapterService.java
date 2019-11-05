package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;

import java.util.Map;

public interface ChapterService {
    Map<String,Object> findAll(Integer page,Integer rows,String albumId);
    String save(Chapter chapter);
    void update(Chapter chapter);
}
