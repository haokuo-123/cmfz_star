package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.Star;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    Map<String,Object> findAll(Integer page,Integer rows);
    String save(Album album);
    void update(Album album);
    List<Star> findAllStar();
    Album findOne(String id);
}
