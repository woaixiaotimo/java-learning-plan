package com.example.demo2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test {


    @RequestMapping("/a")
    @ResponseBody
    public String a() {
        System.out.println("demo2");
        return "demo2";
    }
}
