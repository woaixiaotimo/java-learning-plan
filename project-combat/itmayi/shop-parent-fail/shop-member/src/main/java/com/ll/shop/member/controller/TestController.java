package com.ll.shop.member.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @RequestMapping("/test")
    public String test() {
        JSON.toJSONString("{a:123}");
        return "success";
    }

    @RequestMapping("/exception")
    public String exception() throws Exception {

        throw new Exception("测试");
    }
}
