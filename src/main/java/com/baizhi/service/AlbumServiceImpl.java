package com.baizhi.service;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.dao.StarDAO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Star;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDAO albumDAO;
    @Autowired
    private StarDAO starDAO;
    @Override
    public Map<String, Object> findAll(Integer page, Integer rows) {
        Album album = new Album();
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        List<Album> albums = albumDAO.selectByRowBounds(album, rowBounds);
        int count = albumDAO.selectCount(album);
        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",albums);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);
        return map;
    }

    @Override
    public String save(Album album) {
        album.setId(UUID.randomUUID().toString());
        album.setCreateDate(new Date());
        album.setCount(0);
        int i = albumDAO.insertSelective(album);
        if(i==0){
            throw  new RuntimeException("添加失败");
        }
        return album.getId();
    }

    @Override
    public void update(Album album) {
        if("".equals(album.getCover())){
            album.setCover(null);
        }
        try {
            albumDAO.updateByPrimaryKeySelective(album);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("修改失败");
        }
    }

    @Override
    public List<Star> findAllStar() {
        List<Star> stars = starDAO.selectAll();
        return stars;
    }

    @Override
    public Album findOne(String id) {
        Album album = albumDAO.selectByPrimaryKey(id);
        return  album;
    }
}
