package com.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    //该映射为spring默认首页
    @RequestMapping("/index")
    public String showIndex() {
        return "index";
    }

}
