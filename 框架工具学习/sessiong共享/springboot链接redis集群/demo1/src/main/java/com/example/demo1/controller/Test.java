package com.example.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test {


    @RequestMapping("/a")
    @ResponseBody
    public String a() {
        System.out.println("demo1");
        return "demo1";
    }
}
