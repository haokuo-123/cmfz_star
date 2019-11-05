package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @ResponseBody
    @RequestMapping("login")
    public Map<String,Object> login(Admin admin, String code, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        try {
             adminService.login(admin,code,request);
            map.put("status",true);
        } catch (Exception e) {
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;
    }
    @RequestMapping("exit")
    public String exit(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login/login.jsp";
    }

}
