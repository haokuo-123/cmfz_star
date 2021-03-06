package com.baizhi;

import com.baizhi.dao.BannerDAO;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.Admin;
import com.baizhi.poi.User;
import com.baizhi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SpringBootTest
class CmfzStarApplicationTests {
    @Autowired
    private BannerDAO bannerDAO;
   @Autowired
   private UserDAO userDAO;
   @Autowired
   private UserService userService;
   @Autowired
   private RedisTemplate redisTemplate;

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
    @Test
    public void testString(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("name","zhangsan");
        valueOperations.set("admin",new Admin("99","ss","111111","ss"));
        Object name = valueOperations.get("admin");
        System.out.println(name);
        Admin admin = (Admin) valueOperations.get("admin");
        System.out.println(admin.getId());
        System.out.println(admin.getUsername());
        System.out.println(admin.getPassword());
        System.out.println(admin.getNickname());
    }
    @Test
    public void testList(){
        ListOperations listOperations = redisTemplate.opsForList();
        listOperations.leftPush("aa","123");
        listOperations.leftPush("aa",new Admin("88","kk","1111111","ee"));
        List lsit = listOperations.range("aa", 0, -1);
        for (Object o : lsit) {
            System.out.println(o);
        }
    }
    @Test
    public void testList2(){
        BoundListOperations ops = redisTemplate.boundListOps("aa");
        ops.leftPush("123");
        ops.leftPush(new Admin("55","ssss","232323","ggg"));
        List list = ops.range(0, -1);
        for (Object o : list) {
            System.out.println(o);
        }
    }

}
