package com.baizhi.service;

import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Override
    public Map<String, Object> findAll(Integer page, Integer rows, String starId) {
        User user = new User();
        user.setStarId(starId);
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        List<User> users = userDAO.selectByRowBounds(user,rowBounds);
        int count = userDAO.selectCount(user);
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",users);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);
        return map;
    }

    @Override
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        User user = new User();
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        List<User> users = userDAO.selectByRowBounds(user,rowBounds);
        int count = userDAO.selectCount(user);

        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",users);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);
        return map;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = userDAO.selectAll();
        return users;
    }

    @Override
    public void add(User user,String codeMessage, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String message = (String) session.getAttribute("codeMessage");
        System.out.println(codeMessage);
        System.out.println(message);
        user.setId(UUID.randomUUID().toString());
        user.setCreateDate(new Date());
        if(message.equals(codeMessage)){

            userDAO.insertSelective(user);

        }else{
            throw new RuntimeException("验证码错误");
        }
    }


}
