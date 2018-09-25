package com.demo.demo01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWord {

    @RequestMapping("/index")
    public String index() {
        int a = 1 / 0;
        return "success";
    }

    @RequestMapping("/getMap")
    public Map<String, Object> getMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("errNo", "200");
        result.put("errMsg", "成功");
        return result;
    }
}
