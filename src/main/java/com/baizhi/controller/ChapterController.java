package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import it.sauronsoftware.jave.Encoder;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private AlbumService albumService;
    @RequestMapping("findAll")
    public Map<String,Object> findAll(Integer page,Integer rows,String albumId){
        return chapterService.findAll(page, rows, albumId);
    }

    @RequestMapping("edit")
    public Map<String,Object> edit(Chapter chapter,String oper){
        Map<String, Object> map = new HashMap<>();
        try {
            if("add".equals(oper)){
                String id = chapterService.save(chapter);
                map.put("message",id);
                map.put("status",true);
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("message",e.getMessage());
            map.put("status",false);
        }
        return map;
    }
    @RequestMapping("upload")
    public Map<String,Object> upload(MultipartFile name, String id,String albumId, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        try {
            File file = new File(request.getServletContext().getRealPath("/chapter/music"), name.getOriginalFilename());
            name.transferTo(file);
            Chapter chapter = new Chapter();
            chapter.setId(id);
            chapter.setName(name.getOriginalFilename());
            //文件大小
            BigDecimal size = new BigDecimal(name.getSize());
            BigDecimal mod = new BigDecimal(1024);
            BigDecimal realsize=size.divide(mod).divide(mod).setScale(2,BigDecimal.ROUND_HALF_UP);
            chapter.setSize(realsize+"MB");

            Encoder encoder = new Encoder();
            long duration = encoder.getInfo(file).getDuration();
            chapter.setDuration(duration/1000/60+":"+duration/1000%60);
            chapterService.update(chapter);

            //修改数量
            Album album = albumService.findOne(albumId);
            album.setCount(album.getCount()+1);
            albumService.update(album);
            map.put("status",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",false);
        }
        return map;
    }
}
