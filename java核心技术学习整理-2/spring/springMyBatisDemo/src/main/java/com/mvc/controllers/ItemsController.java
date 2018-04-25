package com.mvc.controllers;

import com.mvc.po.ItemsCustom;
import com.mvc.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/items")
public class ItemsController {
    //url映射
    //限制http请求方法
    //@RequestMapping(value = "/items",method = {RequestMethod.POST,RequestMethod.GET})


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
//-------------------------使用 ModelAndView 返回值进行修改--------------------------------------

//
//    @RequestMapping("/editItems")
//    public ModelAndView editItems() throws Exception {
//        ItemsCustom itemsCustom = itemsService.findItemsById(1);
//
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("itemsCustom", itemsCustom);
//
//        modelAndView.setViewName("/jsp/items/editItems.jsp");
//        return modelAndView;
//    }


//    @RequestMapping("/editItemsSubmit")
//    public ModelAndView editItemsSubmit() throws Exception {
////
////        ItemsCustom itemsCustom = new ItemsCustom();
////        boolean success = itemsService.updateItems(1, itemsCustom);
//
//
//        ModelAndView modelAndView = new ModelAndView();
////        modelAndView.addObject("itemsCustom", itemsCustom);
//
//        modelAndView.setViewName("/jsp/success.jsp");
//        return modelAndView;
//    }


//-------------------------使用 String 返回值进行修改--------------------------------------

    //@RequestParam指定request传入参数和方法的形参进行绑定
    //通过required属性指定参数是否需要传入，如果设为true且未传入则返回400
    //通过defaultValue属性设置默认值，如果参数未传入则使用默认值
    @RequestMapping("/editItems")
    public String editItems(Model model, @RequestParam(value = "id", required = true, defaultValue = "1") Integer items_id) throws Exception {


        ItemsCustom itemsCustom = itemsService.findItemsById(items_id);

        //通过形参的model将model参数传回页面
        model.addAttribute("itemsCustom", itemsCustom);
        return "/jsp/items/editItems.jsp";
    }

    //重定向 转发
    @RequestMapping("/editItemsSubmit")
    public String editItemsSubmit(HttpServletRequest request, HttpServletResponse response, Integer id, ItemsCustom itemsCustom) throws Exception {


        itemsService.updateItems(id, itemsCustom);

        //重定向
        //重定向后地址栏地址会发生变化，但是重定向前修改的request中的数据无法传递到新的地址。因为重定向后重新创建request
        //return "redirect:queryItems.action";

        //转发
        //地址栏地址不变
        //request在两个页面间可以共享，也就是同一个
        return "forward:queryItems.action";
    }


//    //重定向 转发
//    @RequestMapping("/testJson")
//    @ResponseBody
//    public Map<String ,String > testJson() throws Exception {
//        Map map = new HashMap<String ,String >();
//        map.put("a","a");
//        map.put("b","b");
//        map.put("c","c");
//        return map;
//    }

//-------------------------------返回 void-------------------------------------
//    在controller形参上可以定义request和response
//    1、可以使用request转向页面
//        request.getRequestDispatcher("页面路径").forward(request,response)
//    2、也可以通过response页面重定向
//        response.sendRedirect("url");
//    3、也可以通过response返回响应结果
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("application/json;charset=utf-8");
//        response.getWriter().write("json串");


}
