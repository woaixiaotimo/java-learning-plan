package com.ll.serviceorder;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestOrderMemberController {

    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "defaultStores")
    @RequestMapping("/restGetOrderMember")
    public String getOrderMember() {
        return "order - " + restTemplate.getForObject("http://service-member/getMember", String.class);
    }


    public String defaultStores() {

//        System.out.println("parameters = " + parameters);
//        return parameters;
        return "error";
    }

}
