package com.manager.web.controller;

import com.common.pojo.TaotaoResult;
import com.manager.pojo.mybatisPojo.TbItem;
import com.manager.service.ItemService;
import com.common.pojo.EUDataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable long itemId) {
        return itemService.getItemById(itemId);
    }

    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemList(int page, int rows) {
        return itemService.getItemList(page, rows);
    }


    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult createItem(TbItem tbItem,String desc) throws Exception {
        return itemService.createItem(tbItem,desc);
    }
}
