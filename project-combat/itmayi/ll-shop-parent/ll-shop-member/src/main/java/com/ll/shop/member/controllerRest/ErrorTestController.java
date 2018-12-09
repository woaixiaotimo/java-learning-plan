package com.ll.shop.member.controllerRest;

import com.ll.shop.common.beans.ResultBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 啊Q on 2018-12-09.
 */
@RequestMapping("/shop/error")
@RestController
public class ErrorTestController {


    @RequestMapping("/test")
    public ResultBean test() {
        return new ResultBean(new Exception("测试错误返回"));
    }

    @RequestMapping("/test2")
    public ResultBean test2() throws Exception {
        throw new Exception("测试错误返回");
    }
}
