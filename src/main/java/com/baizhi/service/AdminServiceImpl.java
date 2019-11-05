package com.baizhi.service;

import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDAO adminDAO;
    @Override
    public Admin login(Admin admin, String code, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String securityCode = (String) session.getAttribute("securityCode");
        Admin admin2 = new Admin();
        admin2.setUsername(admin.getUsername());
        Admin admin1 = adminDAO.selectOne(admin2);
        if(securityCode.equalsIgnoreCase(code)){
            if(admin1 != null){
                if(admin1.getPassword().equals(admin.getPassword())){session.setAttribute("admin1",admin1);return admin; }throw  new RuntimeException("密码错误");
            }else {
                throw  new RuntimeException("用户不存在");
            }
        }else{
                throw new RuntimeException("验证码错误");
        }
    }
}
