package com.mvc.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//使用该注解标识这个类是控制器
@Controller
@RequestMapping("/c")
public class AnnotationController {
    public AnnotationController() {
        System.out.println("AnnotationController创建");
    }

    //
    @RequestMapping("/test1")
    public ModelAndView test1() {
        System.out.println("使用注解开发，并访问 ModelAndView test1()");


        //创建modelAndView准备填充数据、设置视图
        ModelAndView modelAndView = new ModelAndView();

        //填充数据
        modelAndView.addObject("itemsList", "");
        //视图
        modelAndView.setViewName("/modelAndView/AnnotationModelAndView.jsp");

        return modelAndView;
    }
}
