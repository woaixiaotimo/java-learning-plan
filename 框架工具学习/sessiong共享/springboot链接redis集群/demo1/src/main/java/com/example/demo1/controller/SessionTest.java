package com.example.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SessionTest {

    @RequestMapping(value = "/setSession")
    @ResponseBody
    public String setSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap();
        map.put("name", "超级管理员");
        map.put("account", "admin");
        request.getSession().setAttribute("userSession", map);
        String sessionId = request.getSession().getId();
        System.out.println("demo1 setSession");
        return sessionId;
    }

    @RequestMapping(value = "/getSession")
    @ResponseBody
    public String getSession(HttpServletRequest request) {

        System.out.println("demo1 getSession");
        String sessionId = request.getSession().getId();
        return "demo1  " + sessionId;
    }
}
