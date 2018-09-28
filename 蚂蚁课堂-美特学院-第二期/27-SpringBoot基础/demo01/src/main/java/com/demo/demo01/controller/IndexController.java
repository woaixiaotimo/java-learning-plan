package com.demo.demo01.controller;

import com.demo.demo01.test01.service.UserServiceTest01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private UserServiceTest01 userServiceTest01;

    @RequestMapping("/indexController")
    public String indexController() {
        return "index";
    }


    @ResponseBody
    @RequestMapping("/insertTest001")
    public String insertTest001(String name, Integer age) {
        userServiceTest01.insertTest001(name, age);
        return "success";
    }


}
