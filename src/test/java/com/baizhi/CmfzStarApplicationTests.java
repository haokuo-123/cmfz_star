package com.baizhi;

import com.baizhi.dao.BannerDAO;
import com.baizhi.dao.UserDAO;
import com.baizhi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class CmfzStarApplicationTests {
    @Autowired
    private BannerDAO bannerDAO;
   @Autowired
   private UserDAO userDAO;
   @Autowired
   private UserService userService;

    @Test
    void contextLoads() {

        List<String> num = Arrays.asList("4","6","3","1","5","2") ;
        Collections.sort(num);
        for (String s : num) {
            System.out.print(s+",");
        }
     /*   List<Banner> banners = bannerDAO.selectAll();
        for (Banner banner : banners) {
            System.out.println(banner);
        }*/
       /* List<Admin> admins = adminDAO.selectAll();
        for (Admin admin : admins) {
            System.out.println(admin);
        }*/
    /*   Admin admin = new Admin();
       admin.setId("1");
        List<Admin> list = adminDAO.select(admin);
        for (Admin admin1 : list) {
            System.out.println(admin1);
        }*/
        /*Admin admin = adminDAO.selectByPrimaryKey("1");
        System.out.println(admin);*//*
        Admin admin = new Admin();
        RowBounds rowBounds = new RowBounds(0,2);
        List<Admin> list = adminDAO.selectByRowBounds(admin, rowBounds);
        for (Admin admin1 : list) {
            System.out.println(admin1);
        }*/
    /* Banner banner = new Banner();
     RowBounds rowBounds = new RowBounds(0,2);
     List<Banner> banners =bannerDAO.selectByRowBounds(banner,rowBounds);
        for (Banner banner1 : banners) {
            System.out.println(banner1);
        }*/
/*     Admin admin = new Admin();
        int i = adminDAO.selectCount(admin);
        System.out.println(i);*/
      /*  Example example = new Example(Admin.class);
        example.createCriteria().andBetween("id","1","2").andEqualTo("nickname","哈哈");
        List<Admin> list = adminDAO.selectByExample(example);
        for (Admin admin : list) {
            System.out.println(admin);
        }*/
    /*  Admin admin = new Admin();
      admin.setId(UUID.randomUUID().toString());
      admin.setUsername("lala");
      admin.setPassword("444444");
      admin.setNickname("拉拉");
        int i = adminDAO.insert(admin);
        System.out.println(i);*/
    /* Admin admin = new Admin();
      admin.setId("1");
      admin.setUsername("oo");
      admin.setPassword("000000");
      adminDAO.updateByPrimaryKey(admin);
      adminDAO.updateByPrimaryKeySelective(admin);*/

      //adminDAO.deleteByPrimaryKey("1");
        /*Admin admin = new Admin();
        Admin admin1 = adminDAO.selectOne(admin);
        System.out.println(admin1);*/

    }
    @Test
    public void test(){
      /*  for(int i=1;i<=9;i++){
            for(int j=1;j<=i;j++){
                System.out.print(j+"*"+i+"="+i*j+"\t");
            }
            System.out.println();
        }*/
    }
}
