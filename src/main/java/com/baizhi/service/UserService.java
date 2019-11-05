package com.baizhi.service;

import com.baizhi.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String,Object> findAll(Integer page,Integer rows,String starId);
    Map<String,Object> selectAll(Integer page,Integer rows);
    List<User> findAllUsers();
    void add(User user,String codeMessage, HttpServletRequest request);
}
