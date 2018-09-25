package com.demo.demo01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/indexController")
    public String indexController() {
        return "index";
    }
}
