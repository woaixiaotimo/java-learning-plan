package com.ll.shop.member.controllerRest;

import com.ll.shop.member.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by 啊Q on 2018-12-09.
 */
@RestController
@RequestMapping("/shop/member")
public class TestController {


    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public Map test(Integer id, String name) {
        return testService.test(id, name);
    }

}
