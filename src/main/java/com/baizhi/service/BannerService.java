package com.baizhi.service;

import com.baizhi.entity.Banner;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface BannerService {
    Map<String,Object> findAll(Integer page, Integer rows);
    String save(Banner banner);
    void update(Banner banner);
    void delete(String id, HttpServletRequest request);
}
