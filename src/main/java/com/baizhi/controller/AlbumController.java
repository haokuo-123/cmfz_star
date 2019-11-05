package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Star;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @RequestMapping("findAll")
    public Map<String,Object> findAll(Integer page,Integer rows){
        return albumService.findAll(page, rows);
    }
    @RequestMapping("edit")
    @ResponseBody
    public Map<String,Object> edit(Album album, String oper){
        Map<String, Object> map = new HashMap<>();
        try {
            if("add".equals(oper)){
                String id = albumService.save(album);
                map.put("message",id);
            }
            map.put("status",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",false);
            map.put("message",e.getMessage());

        }
        return map;
    }
    @RequestMapping("upload")
        public Map<String,Object> upload(MultipartFile cover, String id, HttpServletRequest request){
            Map<String, Object> map = new HashMap<>();
            try {
                cover.transferTo(new File(request.getServletContext().getRealPath("/album/img"),cover.getOriginalFilename()));
                Album album = new Album();
                album.setId(id);
                album.setCover(cover.getOriginalFilename());
                albumService.update(album);
                map.put("status",true);
            } catch (IOException e) {
                e.printStackTrace();
                map.put("status",false);
            }
        return map;
    }
    @RequestMapping("findAllStar")
    public List<Star> findAllStar(){
        return albumService.findAllStar();
    }
}
