package com.mvc.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpRequestHandler implements org.springframework.web.HttpRequestHandler {
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        System.out.println("使用HttpRequestHandlerAdapter适配器，适配的实现HttpRequestHandler接口的Controller层 - void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)");

        httpServletRequest.getRequestDispatcher("/HttpRequestHandler/HttpRequestHandler.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
