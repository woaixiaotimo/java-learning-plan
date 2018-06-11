package com.rest.controller;

import com.common.utils.JsonUtils;
import com.rest.pojo.CatResult;
import com.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
* 商品分类列表
* */
@Controller
public class ItemCatController {


    @Autowired
    private ItemCatService itemCatService;

//    @RequestMapping(value = "/itemcat/list",produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
//    @ResponseBody
//    public String getItemCatList(String callBack) {
//        CatResult catResult = itemCatService.getItemCatList();
//        return callBack + "(" + JsonUtils.objectToJson(catResult) + ")";
//    }


//    @RequestMapping(value = "/itemcat/list", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
//    @ResponseBody
//    public Object getItemCatList(String callBack) {
//        CatResult catResult = itemCatService.getItemCatList();
//        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
//        mappingJacksonValue.setJsonpFunction(callBack);
//        return mappingJacksonValue;
//    }
    @RequestMapping("/itemcat/list")
    @ResponseBody
    public Object getItemCatList(String callback) {
        CatResult catResult = itemCatService.getItemCatList();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }

}
