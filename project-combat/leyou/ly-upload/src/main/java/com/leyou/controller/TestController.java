package com.leyou.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("upload")
public class TestController {


    @RequestMapping("test")
    public String test() {
        return "success";
    }
}
