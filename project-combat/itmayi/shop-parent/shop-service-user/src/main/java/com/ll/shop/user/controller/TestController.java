package com.ll.shop.user.controller;


import com.ll.common.beans.PageRequest;
import com.ll.common.beans.PageResponse;
import com.ll.common.beans.ResultBean;
import com.ll.shop.user.entity.User;
import com.ll.shop.user.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/findAll")
    public ResultBean<PageResponse<User>> findAll(@RequestBody PageRequest page) {
        return new ResultBean(testService.findAllUser(page));
    }
}
