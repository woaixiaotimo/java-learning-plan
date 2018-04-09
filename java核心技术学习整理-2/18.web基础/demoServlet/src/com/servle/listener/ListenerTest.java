package com.servle.listener;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class ListenerTest implements HttpSessionListener, ServletContextListener, ServletRequestListener {
    private Log log = new Log();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        log.info("创建一个Session ID为" + session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        log.info("销毁一个Session ID为" + session.getId());
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        log.info("即将启动" + context.getContextPath());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        log.info("即将关闭" + context.getContextPath());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        long time = System.currentTimeMillis() - (Long)request.getAttribute("dateCreated");
        log.info("IP " + request.getRemoteAddr() + " 请求结束 ，用时 " + time);
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        String uri = request.getRequestURI();
        uri = request.getQueryString() == null ? uri : (uri + "?" + request.getQueryString());
        request.setAttribute("dateCreated", System.currentTimeMillis());
        log.info("IP " + request.getRemoteAddr() + " 请求 " + uri);
    }

    private class Log {
        void info(String s) {
            System.out.println(s);
        }
    }
}
