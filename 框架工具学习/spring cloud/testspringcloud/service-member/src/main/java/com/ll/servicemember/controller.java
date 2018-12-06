package com.ll.servicemember;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    @Value("${server.port}")
    int port;

    @RequestMapping("/getMember")
    public String getMember() {
        return "张三 - " + port;
    }
}
