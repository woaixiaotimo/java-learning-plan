package com.imooc.manager.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一错误处理
 *
 * @Auther: what
 * @Date: 2018/12/24 11:00
 * @Description:
 */

@ControllerAdvice(basePackages = "com.imooc.manager.controller")
public class ErrorControllerAdvice {


    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        Map<String, Object> attrs = new HashMap();

        String errCode = e.getMessage();
        ErrorEnum errorEnum = ErrorEnum.getByCode(errCode);

        attrs.put("message", errorEnum.getMessage());
        attrs.put("code", errorEnum.getCode());
        attrs.put("canRetry", errorEnum.getCanRetry());
        attrs.put("type", "advice");

        return new ResponseEntity(attrs, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
