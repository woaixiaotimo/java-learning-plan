package com.mvc.controllers;

import com.mvc.po.ItemsCustom;
import com.mvc.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @RequestMapping("/queryItems")
    public ModelAndView queryItems() throws Exception {
        System.out.println(" =============== ");
        List<ItemsCustom> itemsCustomList = itemsService.findItemList(null);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemsCustomList", itemsCustomList);

        modelAndView.setViewName("/items/queryItems.jsp");
        return modelAndView;
    }
}
