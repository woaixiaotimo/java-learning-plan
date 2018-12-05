package com.ll.servicemember.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @RequestMapping("/getMember")
    public String getMember() {
        return "张三";
    }
}
