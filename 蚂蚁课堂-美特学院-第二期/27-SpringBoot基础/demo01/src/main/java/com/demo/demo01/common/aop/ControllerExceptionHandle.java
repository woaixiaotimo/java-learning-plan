package com.demo.demo01.common.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 处理Controller抛出所有异常
 */
@ControllerAdvice
public class ControllerExceptionHandle {

    /**
     * 捕捉具体类型异常
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public String handleException2(Throwable e) {
        System.out.println("e = " + e);
        return "捕捉数字除以0产生的错误！";
    }


    /**
     * 在此处捕捉Controller抛出异常
     */
    @ExceptionHandler
    @ResponseBody
    public String handleException(Throwable e) {

        // 已知异常
        if (e instanceof ArithmeticException) {
            System.out.println("捕捉到ArithmeticException类型异常");
        }

        System.out.println("e = " + e);
        return "再次捕捉所有异常!";
    }


}
