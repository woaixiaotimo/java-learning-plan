package com.servle.Filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class AntiTheftChainFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("防盗链过滤");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(servletRequest, servletResponse);

//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        //链接来源地址
//        String referer = request.getHeader("referer");
//        System.out.println("referer = " + referer);
//        Enumeration<String> enumeration = request.getHeaderNames();
//
//        while (enumeration.hasMoreElements()) {
//            System.out.println("enumeration.nextElement() = " + enumeration.nextElement());
//        }
//
//
//        if (referer == null || !referer.contains(request.getServerName())) {
//            //转发一个错误图片
//            request.getRequestDispatcher("/err.jpg").forward(request, response);
//        } else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }


    }

    @Override
    public void destroy() {
        System.out.println("防盗链结束");
    }
}
