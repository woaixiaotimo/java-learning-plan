package com.controller;

import com.servle.Service;
import com.servle.ControllerUrl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class Controller {


    @ControllerUrl("/test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("test");
        //获取请求中的参数的值
        request.getParameter("key");
        //获取域对象中的值(jsp和)
        request.getAttribute("key");
        //重定向-客户端请求->服务器返回状态码->客户端重新请求新网址(网址以原服务器域名开头)
        response.sendRedirect("/ServletDemo/Login.jsp");
        //转发-客户端请求数据，地址栏不变，可以共享request中的数据，效率较高
        request.getRequestDispatcher("/Login.jsp").forward(request, response);
        //返回ajax请求的数据
        ServletOutputStream stream = response.getOutputStream();
        stream.write("发送的ajax数据".getBytes("UTF-8"));
        stream.flush();

    }

    @ControllerUrl("/forward")
    public void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("转发");
        request.getRequestDispatcher("/forward.jsp").forward(request, response);
    }

    @ControllerUrl("/sendRedirect")
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        System.out.println("重定向");
        response.sendRedirect("/sendRedirect.jsp");
    }

    @ControllerUrl("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        ServletOutputStream stream = response.getOutputStream();
        stream.write("login success".getBytes("UTF-8"));
        stream.flush();
    }
}
