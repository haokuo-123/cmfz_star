package com.baizhi.controller;

import com.baizhi.entity.Star;
import com.baizhi.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("star")
public class StarController {
    @Autowired
    private StarService starService;
    @RequestMapping("findAll")
    public Map<String,Object> findAll(Integer page,Integer rows){
        Map<String,Object> map = starService.findAll(page,rows);
        return map;
    }
    @RequestMapping("edit")
    public Map<String,Object> edit(Star star,String oper){
        Map<String,Object> map = new HashMap<>();
        try {
            if("add".equals(oper)){
                String id = starService.save(star);
                map.put("message",id);
                map.put("status",true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;
    }
@RequestMapping("upload")
    public Map<String,Object> upload(MultipartFile photo, String id, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        try {
            photo.transferTo(new File(request.getServletContext().getRealPath("/star/img"),photo.getOriginalFilename()));
            Star star = new Star();
            star.setId(id);
            star.setPhoto(photo.getOriginalFilename());
            starService.update(star);
            map.put("status",true);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("status",false);
        }
        return map;
    }
}
