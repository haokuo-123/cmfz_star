package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("selectUsersByStarId")
    public Map<String,Object> selectUsersByStarId(Integer page,Integer rows,String starId){
        return userService.findAll(page, rows, starId);
    }
    @RequestMapping("selectAll")
    public Map<String,Object> selectAll(Integer page,Integer rows){
        return userService.selectAll(page, rows);
    }
    @RequestMapping("export")
    public void export(HttpServletResponse resp){
        List<User> users = userService.findAllUsers();
        for (User user : users) {
            user.setPhoto("http://localhost:8989/star/user/img/"+user.getPhoto());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("所有用户","用户表"),com.baizhi.entity.User.class,users);
        String fileName = "用户报表("+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+").xls";
        //处理中文下载名乱码
        try {
            fileName = new String(fileName.getBytes("gbk"),"iso-8859-1");
            //设置 response
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("content-disposition","attachment;filename="+fileName);
            workbook.write(resp.getOutputStream());
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("findBySex")
  public Map<String,Object> findBySex(){
        return null;
    }

    @RequestMapping("add")
    public void add(User user, String codeMessage, HttpServletRequest request){
        userService.add(user,codeMessage,request);
    }
}
