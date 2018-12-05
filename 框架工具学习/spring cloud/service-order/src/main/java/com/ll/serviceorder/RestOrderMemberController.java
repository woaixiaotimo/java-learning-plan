package com.ll.serviceorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestOrderMemberController {

    @Autowired
    private RestTemplate restTemplate;



    @RequestMapping("/restGetOrderMember")
    public String getOrderMember() {
        return "order - " + restTemplate.getForObject("http://service-member/getMember", String.class);
    }

}
