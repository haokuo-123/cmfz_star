package com.baizhi.service;

import com.baizhi.dao.BannerDAO;
import com.baizhi.entity.Banner;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDAO bannerDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> findAll(Integer page, Integer rows) {
        Banner banner = new Banner();
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        List<Banner> banners = bannerDAO.selectByRowBounds(banner,rowBounds);
        int count = bannerDAO.selectCount(banner);
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",banners);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);
        return map;
    }
    @Override
    public String save(Banner banner) {
        banner.setId(UUID.randomUUID().toString());
        banner.setCreateDate(new Date());
        int i = bannerDAO.insert(banner);
        if(i==0){
            throw new RuntimeException("添加失败");
        }
        return banner.getId();
    }

    @Override
    public void update(Banner banner) {
        if("".equals(banner.getCover())){
            banner.setCover(null);
        }
        try {
            bannerDAO.updateByPrimaryKeySelective(banner);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public void delete(String id, HttpServletRequest request) {
        Banner banner = bannerDAO.selectByPrimaryKey(id);
        int i = bannerDAO.deleteByPrimaryKey(id);
        if(i==0){
            throw  new RuntimeException("删除失败");
        }else {
            String cover = banner.getCover();
            File file = new File(request.getServletContext().getRealPath("banner/img"),cover);
            boolean b = file.delete();
            if(b==false){
                throw new  RuntimeException("删除轮播图失败");
            }
        }
    }
}
