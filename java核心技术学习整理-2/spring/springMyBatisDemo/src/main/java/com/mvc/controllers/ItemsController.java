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
        List<ItemsCustom> itemsList = itemsService.findItemList(null);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemsList", itemsList);

        modelAndView.setViewName("/jsp/items/itemsList.jsp");
        return modelAndView;
    }


    @RequestMapping("/queryItems")
    public ModelAndView queryItems() throws Exception {
        ItemsCustom itemsCustom = itemsService.findItemsById();
    }

}
