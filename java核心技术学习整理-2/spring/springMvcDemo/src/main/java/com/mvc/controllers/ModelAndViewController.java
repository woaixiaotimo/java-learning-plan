package com.mvc.controllers;

import com.mvc.po.Items;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ModelAndViewController implements Controller {


    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {


        //商品列表
        List<Items> itemsList = new ArrayList<Items>();

        Items items_1 = new Items();
        items_1.setName("联想笔记本");
        items_1.setPrice(6000f);
        items_1.setDetail("ThinkPad T430 联想笔记本电脑！");

        Items items_2 = new Items();
        items_2.setName("苹果手机");
        items_2.setPrice(5000f);
        items_2.setDetail("iphone6苹果手机！");

        itemsList.add(items_1);
        itemsList.add(items_2);

        //创建modelAndView准备填充数据、设置视图
        ModelAndView modelAndView = new ModelAndView();

        //填充数据
        modelAndView.addObject("itemsList", itemsList);
        //视图
        modelAndView.setViewName("/modelAndView/modelAndView.jsp");

        return modelAndView;

    }
}
