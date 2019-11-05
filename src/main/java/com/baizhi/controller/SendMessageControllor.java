package com.baizhi.controller;

import com.baizhi.aliyun.SendMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RequestMapping("sendMessage")
@RestController
public class SendMessageControllor {
    @RequestMapping("sendCode")
    public void sendCode(HttpServletRequest request,String phone) throws Exception {
       String code = String.valueOf((int)((Math.random()*9+1)*1000));
        String codeMessage = SendMessage.sendMessage(code,phone);
        HttpSession session = request.getSession();
        session.setAttribute("codeMessage",codeMessage);
    }
}
