package com.ll.shop.user.controller;

import com.ll.common.beans.ResultBean;
import com.ll.shop.user.entity.User;
import com.ll.shop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findById")
    public ResultBean<User> findById(Long id) {
        return new ResultBean<User>(userService.findById(id));
    }


    @RequestMapping("/addUser")
    public ResultBean<Integer> addUser(@RequestBody User user) throws Exception {

        return new ResultBean<Integer>(userService.addUser(user));
    }

}
