package com.baizhi.service;

import com.baizhi.entity.Admin;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {
    Admin login(Admin admin, String code, HttpServletRequest request);
}
